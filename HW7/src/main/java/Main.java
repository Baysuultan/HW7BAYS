import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Episode> fakeEpisodes = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            fakeEpisodes.add(new Episode("Episode " + i, 1200 + (i % 300)));
        }

        System.out.println("\nPerformance Report:");

        long start = System.nanoTime();
        for (Episode e : fakeEpisodes) {
            e.getTitle();
        }
        long end = System.nanoTime();
        long normalOrderTime = (end - start) / 1_000_000;
        System.out.println("Normal Order: " + normalOrderTime + " ms");

        ReverseSeasonIterator reverseIterator = new ReverseSeasonIterator(fakeEpisodes);
        start = System.nanoTime();
        while (reverseIterator.hasNext()) {
            reverseIterator.next().getTitle();
        }
        end = System.nanoTime();
        long reverseOrderTime = (end - start) / 1_000_000;
        System.out.println("Reverse Order: " + reverseOrderTime + " ms");

        ShuffleSeasonIterator shuffleIterator = new ShuffleSeasonIterator(fakeEpisodes, 42);
        start = System.nanoTime();
        while (shuffleIterator.hasNext()) {
            shuffleIterator.next().getTitle();
        }
        end = System.nanoTime();
        long shuffleOrderTime = (end - start) / 1_000_000;
        System.out.println("Shuffle Order: " + shuffleOrderTime + " ms");

        System.out.println("\nASCII Performance Graph:");
        System.out.println("Normal Order:   " + String.join("", Collections.nCopies((int) normalOrderTime, "#")));
        System.out.println("Reverse Order:  " + String.join("", Collections.nCopies((int) reverseOrderTime, "#")));
        System.out.println("Shuffle Order:  " + String.join("", Collections.nCopies((int) shuffleOrderTime, "#")));
    }
}