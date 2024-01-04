package auth.cableTv.domain;

import java.util.List;


public class Season {
    private int seasonNumber;
    private int releaseYear;
    private List<Episode> episodes;

    public Season(int seasonNumber, int releaseYear) {
        this.seasonNumber = seasonNumber;
        this.releaseYear = releaseYear;
    }

    public Season(int seasonNumber, int releaseYear, List<Episode> episodes) {
        this.seasonNumber = seasonNumber;
        this.releaseYear = releaseYear;
        this.episodes = episodes;
    }

    public Season() {
    }

    @Override
    public String toString() {
        return "Season(seasonNumber=" + seasonNumber +
                ", releaseYear=" + releaseYear +
                ", episodes=" + getEpisodes() +
                ")";
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
