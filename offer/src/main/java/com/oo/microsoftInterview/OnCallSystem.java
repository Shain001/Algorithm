package com.oo.microsoftInterview;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 微软二轮面试， SDE II   2024 May
 *
 * 设计一个 oncall 系统， 系统中有三种不同的 employers,  worker, lead, and boss.
 *
 * incident 最开始会分配给 一个 worker， worker 可以选择自己处理， 或者escalate 给一个 lead.
 * lead 也可以选择自己处理或者 escalate 给 boss。
 * boss必须处理收到的incident
 *
 * 如果 没有 available 的worker则发送给lead， 如果lead也都不available， 直接发送给boss。
 *
 *
 * 重点：
 * 1。 策略模式的应用。
 *
 * 策略模式 详解： https://refactoringguru.cn/design-patterns/strategy
 *
 * 解释很抽象， 但是basically speaking, 当 三种不同的 employer 处理请求的时候， 逻辑基本相同， 只有 boss稍微有点区别， 即必须处理。
 * 此处使用 策略模式， 定义一个strategy 接口， 然后 分别创建 三个class implements 这个接口， 每个class则是对应的员工种类的 应对逻辑。
 *
 * 然后在 handlerEmployee class 中设置 strategy field， 直接调用该接口处理 incident。
 *
 * 2。 incidengHandler 接口 和  Employee 抽象类的设置。
 *
 * 三种员工都属于 Employee, 有共同的属性， 所以抽象一个parent class。
 * 但是 从系统扩展的角度考虑， 并非所有的Employee都可能是可以处理incdient的类型， 所以再创建一个 incidentHandler interface， 让本题中的
 * 三种员工calss 都implement这个接口
 *
 */
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
        throw new NoAvailableHandlerException();
    }

    private boolean assignIncident(IncidentHandler e, Incident incident) {
        e.handleIncident(incident);
        return incident.status == Incident.Status.PROCESSED;
    }

    static class NoAvailableHandlerException extends RuntimeException {
        public NoAvailableHandlerException() {
            super("No Available Handler");
        }
    }

    class Incident {
        enum Status {
            PROCESSED, PENDING, IN_PROCESS
        }

        private String incidentId;
        private HandlerEmployee handler;
        private Status status;

        public Incident() {
            this.status = Status.PENDING;
            this.incidentId = UUID.randomUUID().toString();
        }

        private void setHandler(HandlerEmployee handler) {
            this.handler = handler;
            this.status = Status.IN_PROCESS;
            System.out.printf("Incident %s is currently handled by %s, employeeId %s%n", incidentId, handler.position.toString(), handler.employeeId);
        }

        private void closeIncident() {
            this.status = Status.PROCESSED;
            System.out.printf("Incident %s is closed, final handler is %s, employeeId %s%n", incidentId, handler.position.toString(), handler.employeeId);
        }
    }

    interface IncidentHandler {
        void handleIncident(Incident incident);
    }

    abstract class Employee {
        enum Position {
            WORKER, LEAD, BOSS
        }

        protected String employeeId;
        protected Position position;

        public Employee(Position position) {
            this.employeeId = UUID.randomUUID().toString();
            this.position = position;
        }
    }

    abstract class HandlerEmployee extends Employee implements IncidentHandler {

        protected boolean isAvailable;
        protected Deque<Incident> handledIncidents;
        protected IncidentHandleStrategy strategy;

        public HandlerEmployee(Position position, IncidentHandleStrategy strategy) {
            super(position);
            this.isAvailable = true;
            this.handledIncidents = new LinkedList<>();
            this.strategy = strategy;
        }

        public final void handleIncident(Incident incident) {
            incident.setHandler(this);
            this.handledIncidents.offerLast(incident);
            this.strategy.doHandleIncident(incident, this);
        }
    }

    class Boss extends HandlerEmployee {
        public Boss() {
            super(Position.BOSS, new BossIncidentHandleStrategy());
        }
    }

    class Worker extends HandlerEmployee {
        private final List<Lead> knownLeads;

        public Worker(List<Lead> leads) {
            super(Position.WORKER, new WorkerIncidentHandleStrategy());
            this.knownLeads = leads;
        }
    }

    class Lead extends HandlerEmployee {
        private final List<Boss> knownBosses;

        public Lead(List<Boss> bosses) {
            super(Position.LEAD, new LeadIncidentHandleStrategy());
            this.knownBosses = bosses;
        }
    }

    interface IncidentHandleStrategy {
        void doHandleIncident(Incident incident, HandlerEmployee handler);
    }

    class WorkerIncidentHandleStrategy implements IncidentHandleStrategy {

        @Override
        public void doHandleIncident(Incident incident, HandlerEmployee handler) {
            if (Math.random() < 0.5) {
                incident.closeIncident();
            } else {
                handler.handledIncidents.pollLast();
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
        public void doHandleIncident(Incident incident, HandlerEmployee handler) {
            incident.closeIncident();
        }
    }

    class LeadIncidentHandleStrategy implements IncidentHandleStrategy {

        @Override
        public void doHandleIncident(Incident incident, HandlerEmployee handler) {
            if (Math.random() < 0.6) {
                incident.closeIncident();
            } else {
                handler.handledIncidents.pollLast();
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
