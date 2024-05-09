package com.design.microsoftInterview;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnCallSystem {
    private List<Boss> bosses;
    private List<Worker> workers;
    private List<Lead> leads;
    private List<Incident> incidents;

    public OnCallSystem(int numberOfBosses, int numberOfWorkers, int numberOfLeads) {
        this.bosses = Stream.generate(Boss::new).limit(numberOfBosses).collect(Collectors.toList());
        this.leads = Stream.generate(() -> new Lead(bosses)).limit(numberOfLeads).collect(Collectors.toList());
        this.workers = Stream.generate(() -> new Worker(leads)).limit(numberOfWorkers).collect(Collectors.toList());
        this.incidents = new ArrayList<>();
    }

    public boolean handleIncident() throws Exception {
        var incident = new Incident();

        for (Worker w : workers) {
            if (w.isAvailable) {
                return assignIncident(w, incident);
            }
        }

        for (Lead l : leads) {
            if (l.isAvailable) {
                return assignIncident(l, incident);
            }
        }

        for (Boss b : bosses) {
            if (b.isAvailable) {
                return assignIncident(b, incident);
            }
        }
        throw new NoAvailableHandlerException();
    }

    class NoAvailableHandlerException extends RuntimeException {
        public NoAvailableHandlerException() {
            super("No Available Handler");
        }
    }

    private boolean assignIncident(IncidentHandler e, Incident incident) {
        e.handleIncident(incident);
        return incident.status == Incident.Status.PROCESSED;
    }

    class Incident {
        enum Status {
            PROCESSED,
            PENDING,
            IN_PROCESS
        }

        private String incidentId;
        private IncidentHandler handler;
        private Status status;

        public Incident() {
            this.status = Status.PENDING;
            this.incidentId = UUID.randomUUID().toString();
        }

        private void setHandler(IncidentHandler handler) {
            this.handler = handler;
            this.status = Status.IN_PROCESS;
            System.out.printf("Incident %s is currently handled by %s, employeeId %s%n", incidentId, handler.position.toString(), handler.employeeId);
        }

        private void closeIncident() {
            this.status = Status.PROCESSED;
            System.out.printf("Incident %s is closed, final handler is %s, employeeId %s%n", incidentId, handler.position.toString(), handler.employeeId);
        }
    }

    interface handler {
        void handleIncident(Incident incident);
    }

    abstract class Employee {
        enum Position {
            WORKER,
            LEAD,
            BOSS
        }

        protected String employeeId;
        protected Position position;

        public Employee(Position position) {
            this.employeeId = UUID.randomUUID().toString();
            this.position = position;
        }
    }

    abstract class IncidentHandler extends Employee implements handler {

        protected boolean isAvailable;
        protected Deque<Incident> incidents;
        protected IncidentHandleStrategy strategy;

        public final void handleIncident(Incident incident) {
            incident.setHandler(this);
            this.incidents.offerLast(incident);
            this.strategy.handle(incident, this);
        }

        public IncidentHandler(Position position, IncidentHandleStrategy strategy) {
            super(position);
            this.isAvailable = true;
            this.incidents = new LinkedList<>();
            this.strategy = strategy;
        }
    }

    class Boss extends IncidentHandler {
        public Boss() {
            super(Position.BOSS, new BossIncidentHandleStrategy());
        }
    }

    class Worker extends IncidentHandler {
        private final List<Lead> knownLeads;

        public Worker(List<Lead> leads) {
            super(Position.WORKER, new WorkerIncidentHandleStrategy());
            this.knownLeads = leads;
        }
    }

    class Lead extends IncidentHandler {
        private final List<Boss> knownBosses;

        public Lead(List<Boss> bosses) {
            super(Position.LEAD, new LeadIncidentHandleStrategy());
            this.knownBosses = bosses;
        }
    }

    interface IncidentHandleStrategy {
        void handle(Incident incident, IncidentHandler handler);
    }

    class WorkerIncidentHandleStrategy implements IncidentHandleStrategy {

        @Override
        public void handle(Incident incident, IncidentHandler handler) {
            if (Math.random() < 0.5) {
                incident.closeIncident();
            } else {
                handler.incidents.pollLast();
                for (Lead lead : ((Worker) handler).knownLeads) {
                    if (lead.isAvailable) {
                        lead.handleIncident(incident);
                        return;
                    }
                }
            }
        }
    }

    class BossIncidentHandleStrategy implements IncidentHandleStrategy {

        @Override
        public void handle(Incident incident, IncidentHandler handler) {
            incident.closeIncident();
        }
    }

    class LeadIncidentHandleStrategy implements IncidentHandleStrategy {

        @Override
        public void handle(Incident incident, IncidentHandler handler) {
            if (Math.random() < 0.6) {
                incident.closeIncident();
            } else {
                handler.incidents.pollLast();
                for (Boss b : ((Lead) handler).knownBosses) {
                    if (b.isAvailable) {
                        b.handleIncident(incident);
                        return;
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        var onCallSystem = new OnCallSystem(1, 10, 4);
        onCallSystem.handleIncident();
    }
}
