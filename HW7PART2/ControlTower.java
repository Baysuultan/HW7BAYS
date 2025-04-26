import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class ControlTower implements TowerMediator {
    private Queue<Aircraft> landingQueue = new LinkedList<>();
    private Queue<Aircraft> takeoffQueue = new LinkedList<>();
    private List<Aircraft> allAircraft = new ArrayList<>();

    @Override
    public void broadcast(String msg, Aircraft sender) {
        System.out.println("Broadcast from " + sender.id + ": " + msg);
        for (Aircraft aircraft : allAircraft) {
            if (!aircraft.equals(sender)) {
                aircraft.receive(msg);
            }
        }
    }

    @Override
    public boolean requestRunway(Aircraft a) {
        if ("MAYDAY".equals(a.id)) {
            System.out.println("Emergency aircraft " + a.id + " granted immediate runway access.");
            clearRunwayForEmergency();
            return true;
        }
        if (!landingQueue.isEmpty()) {
            Aircraft next = landingQueue.poll();
            System.out.println("Runway granted to landing aircraft: " + next.id);
            return true;
        } else if (!takeoffQueue.isEmpty()) {
            Aircraft next = takeoffQueue.poll();
            System.out.println("Runway granted to takeoff aircraft: " + next.id);
            return true;
        }
        return false;
    }

    public void addToLandingQueue(Aircraft a) {
        landingQueue.add(a);
        allAircraft.add(a);
    }

    public void addToTakeoffQueue(Aircraft a) {
        takeoffQueue.add(a);
        allAircraft.add(a);
    }

    private void clearRunwayForEmergency() {
        landingQueue.clear();
        takeoffQueue.clear();
        broadcast("Runway cleared for emergency landing.", null);
    }
}