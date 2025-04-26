import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Season implements Iterable<Episode> {
    private List<Episode> episodes;

    public Season() {
        this.episodes = new ArrayList<>();
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public Iterator<Episode> iterator() {
        return new Iterator<Episode>() {
            private final EpisodeIterator episodeIterator = new SeasonIterator(episodes);

            @Override
            public boolean hasNext() {
                return episodeIterator.hasNext();
            }

            @Override
            public Episode next() {
                return episodeIterator.next();
            }
        };
    }
}