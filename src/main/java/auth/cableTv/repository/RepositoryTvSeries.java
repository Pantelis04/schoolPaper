package auth.cableTv.repository;

import auth.cableTv.domain.TvSeries;

import java.util.ArrayList;
import java.util.List;

public class RepositoryTvSeries {

    Repository repository = new Repository();
    ObjectParser objectParser = new ObjectParser();
    String tvSeriesFile = "tvseries.txt";

    public List<TvSeries> findTvSeries(String title, String description, String genre, String suitability, List<String> actors, String releaseYear, List<String> relatedTvSeries) {
        List<String> stringList = new ArrayList<>();
        stringList.add("title=" + title);
        stringList.add("genre=" + genre);
        stringList.add("description=" + description);
        stringList.add("releaseYear=" + releaseYear);
        stringList.add("suitability=" + suitability);
        for (String actor : actors) {
            stringList.add(actor);
        }
        for (String titleRelated : relatedTvSeries) {
            stringList.add(titleRelated);
        }

        List<String> tvSeriesString = repository.getLines(tvSeriesFile, stringList);

        List<TvSeries> tvSeries = new ArrayList<>();
        for (String seriesString : tvSeriesString) {
            TvSeries tvSeriesFound = objectParser.parseTvSeries(seriesString);
            if (tvSeriesFound.getTitle().contains(title) && tvSeriesFound.getGenre().contains(genre) && tvSeriesFound.getDescription().contains(description)) {
                tvSeries.add(tvSeriesFound);
            }
        }
        return tvSeries;
    }

    public int deleteTvSeries(TvSeries tvSeries) {
        repository.deleteLineByString(tvSeriesFile, tvSeries.getTitle());
        return 1;
    }

    public int updateTvSeries(String tvSeriesTitle, TvSeries newTvSeries) {
        repository.updateLineByString(tvSeriesFile, tvSeriesTitle, newTvSeries.toString());
        return 1;
    }

    public int saveTvSeries(TvSeries tvSeries) {
        repository.saveLine(tvSeriesFile, tvSeries.toString());
        return 1;
    }


}
