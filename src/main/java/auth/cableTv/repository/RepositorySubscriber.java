package auth.cableTv.repository;

import auth.cableTv.domain.Review;
import auth.cableTv.domain.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class RepositorySubscriber {

    Repository repository = new Repository();
    ObjectParser objectParser = new ObjectParser();
    String subscriberFile = "subscriber.txt";

    public Subscriber findSubscriber(String username) {
        List<String> stringList = new ArrayList<>();
        stringList.add("username=" + username);

        Subscriber subscriber = null;
        List<String> subString = repository.getLines(subscriberFile, stringList);
        if (!subString.isEmpty()) {
            subscriber = objectParser.parseSubscriberString(subString.get(0));
        }

        return subscriber;
    }

    public Review findReview(String username, String title) {
        List<String> stringList = new ArrayList<>();
//        stringList.add("title=" + title);
        stringList.add("username=" + username);

        Subscriber subscriber = null;
        List<String> subString = repository.getLines(subscriberFile, stringList);
        if (!subString.isEmpty()) {
            subscriber = objectParser.parseSubscriberString(subString.get(0));
        }


        for (Review review : subscriber.getReviews()) {
            if (review.getTitle().equals(title)) {
                return review;
            }
        }
        return null;
    }

    public int updateSubscriber(String username, Subscriber subscriber) {
        repository.updateLineByString(subscriberFile, username, subscriber.toString());
        return 1;
    }

    public void addASubscriber(Subscriber subscriber) {
        repository.saveLine(subscriberFile, subscriber.toString());
    }

    private boolean listAContainsListB(List<String> listA, List<String> listB) {
        for (String value : listB) {
            if (!listA.contains(value)) {
                return false;
            }
        }
        return true;
    }


}
