package auth.cableTv.repository;

import auth.cableTv.domain.Media;

import java.util.List;

public interface MediaRepository {

    public int saveMedia(Media media);
    public Media getMediaById(int mediaId);

    public List<Media> getAllMedia();

    public void updateMedia(Media media);

    public void deleteMedia(int mediaId);
}
