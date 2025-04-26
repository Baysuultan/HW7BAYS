import java.util.Iterator;
import java.util.List;

public class SeasonIterator implements EpisodeIterator {
    private final Iterator<Episode> iterator;

    public SeasonIterator(List<Episode> episodes) {
        this.iterator = episodes.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Episode next() {
        return iterator.next();
    }
}