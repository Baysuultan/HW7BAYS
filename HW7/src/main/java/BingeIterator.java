import java.util.Iterator;
import java.util.List;

public class BingeIterator implements EpisodeIterator {
    private final Iterator<Season> seasonIterator;
    private EpisodeIterator currentSeasonIterator;

    public BingeIterator(List<Season> seasons) {
        this.seasonIterator = seasons.iterator();
        if (seasonIterator.hasNext()) {
            currentSeasonIterator = new SeasonIterator(seasonIterator.next().getEpisodes());
        }
    }

    @Override
    public boolean hasNext() {
        while ((currentSeasonIterator == null || !currentSeasonIterator.hasNext()) && seasonIterator.hasNext()) {
            currentSeasonIterator = new SeasonIterator(seasonIterator.next().getEpisodes());
        }
        return currentSeasonIterator != null && currentSeasonIterator.hasNext();
    }

    @Override
    public Episode next() {
        if (hasNext()) {
            return currentSeasonIterator.next();
        }
        return null;
    }
}