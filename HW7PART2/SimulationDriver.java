import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SimulationDriver {
    public static void main(String[] args) {
        ControlTower tower = new ControlTower();
        Random random = new Random();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            int aircraftType = random.nextInt(3);
            Aircraft aircraft;
            switch (aircraftType) {
                case 0:
                    aircraft = new PassengerPlane("P" + random.nextInt(100));
                    break;
                case 1:
                    aircraft = new CargoPlane("C" + random.nextInt(100));
                    break;
                default:
                    aircraft = new Helicopter("H" + random.nextInt(100));
                    break;
            }

            if (ThreadLocalRandom.current().nextInt(10) == 0) {
                aircraft.send("MAYDAY", tower);
            } else if (random.nextBoolean()) {
                System.out.println(aircraft.id + " requests landing.");
                tower.addToLandingQueue(aircraft);
            } else {
                System.out.println(aircraft.id + " requests takeoff.");
                tower.addToTakeoffQueue(aircraft);
            }

            tower.requestRunway(aircraft);
        };

        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scheduler.shutdown();
            System.out.println("Simulation ended.");
        }));
    }
}