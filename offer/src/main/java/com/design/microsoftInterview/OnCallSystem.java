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

    public boolean handleIncident() {
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

        return false;
    }

    private boolean assignIncident(Employee e, Incident incident) {
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
        private Employee handler;
        private Status status;

        public Incident() {
            this.status = Status.PENDING;
            this.incidentId = UUID.randomUUID().toString();
        }

        private void setHandler(Employee handler) {
            this.handler = handler;
            this.status = Status.IN_PROCESS;
            System.out.printf("Incident %s is currently handled by %s, employeeId %s%n", incidentId, handler.position.toString(), handler.employeeId);
        }

        private void closeIncident() {
            this.status = Status.PROCESSED;
            System.out.printf("Incident %s is closed, final handler is %s, employeeId %s%n", incidentId, handler.position.toString(), handler.employeeId);
        }
    }

    abstract class Employee {
        enum Position {
            WORKER,
            LEAD,
            BOSS
        }

        protected String employeeId;
        protected boolean isAvailable;
        protected Deque<Incident> incidents;
        protected Position position;

        protected final void handleIncident(Incident incident) {
            incident.setHandler(this);
            this.incidents.offerLast(incident);
            doHandle(incident);
        }

        abstract protected void doHandle(Incident incident);

        public Employee(Position position) {
            this.employeeId = UUID.randomUUID().toString();
            this.isAvailable = true;
            this.incidents = new LinkedList<>();
            this.position = position;
        }
    }

    class Boss extends Employee {
        public Boss() {
            super(Position.BOSS);
        }

        @Override
        protected void doHandle(Incident incident) {
            incident.closeIncident();
        }
    }

    class Worker extends Employee {
        private final List<Lead> knownLeads;

        public Worker(List<Lead> leads) {
            super(Position.WORKER);
            this.knownLeads = leads;
        }

        @Override
        protected void doHandle(Incident incident) {
            if (Math.random() < 0.5) {
                incident.closeIncident();
            } else {
                incidents.pollLast();
                for (Lead lead: knownLeads) {
                    if (lead.isAvailable) {
                        lead.handleIncident(incident);
                        return;
                    }
                }
            }
        }
    }

    class Lead extends Employee {
        private final List<Boss> knownBosses;
        public Lead(List<Boss> bosses) {
            super(Position.LEAD);
            this.knownBosses = bosses;
        }

        @Override
        protected void doHandle(Incident incident) {
            if (Math.random() < 0.6) {
                incident.closeIncident();
            } else {
                incidents.pollLast();
                for (Boss b: knownBosses) {
                    if (b.isAvailable) {
                        b.handleIncident(incident);
                        return;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        var onCallSystem = new OnCallSystem(1, 10, 4);
        onCallSystem.handleIncident();
    }
}
