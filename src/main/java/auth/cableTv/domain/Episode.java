package auth.cableTv.domain;

public class Episode {
    private int episodeNumber;
    private int duration;

    public Episode(int episodeNumber, int duration) {
        this.episodeNumber = episodeNumber;
        this.duration = duration;
    }

    public Episode() {
    }

    @Override
    public String toString() {
        return "Episode(episodeNumber=" + episodeNumber +
                ", duration=" + duration +
                ")";
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
