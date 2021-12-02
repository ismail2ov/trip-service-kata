package tripservicekata;

import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.trip.Trip;
import tripservicekata.trip.TripRepository;
import tripservicekata.user.User;
import tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class OriginalTripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = UserSession.getInstance().getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = TripRepository.findTripsByUser(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }
}
