import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffledEpisodes;
    private int currentIndex;

    public ShuffleSeasonIterator(List<Episode> episodes, long seed) {
        this.shuffledEpisodes = new ArrayList<>(episodes);
        Collections.shuffle(this.shuffledEpisodes, new Random(seed));
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < shuffledEpisodes.size();
    }

    @Override
    public Episode next() {
        return shuffledEpisodes.get(currentIndex++);
    }
}