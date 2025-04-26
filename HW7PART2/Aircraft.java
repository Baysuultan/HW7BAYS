public abstract class Aircraft {
    protected String id;

    public Aircraft(String id) {
        this.id = id;
    }

    public abstract void receive(String msg);

    public void send(String msg, TowerMediator mediator) {
        mediator.broadcast(msg, this);
    }

    public void sendMayday(TowerMediator mediator) {
        System.out.println(id + " sending MAYDAY!");
        mediator.broadcast("MAYDAY", this);
    }
}