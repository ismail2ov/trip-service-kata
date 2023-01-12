package tripservicekata.trip;

import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;
import tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();

        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        if (!areFriend(user, loggedUser)) {
            return new ArrayList<>();
        }

        return getTripsFor(user);
    }

    private boolean areFriend(User user, User loggedUser) {
        return user.getFriends().stream().anyMatch(friend -> friend.equals(loggedUser));
    }

    protected List<Trip> getTripsFor(User user) {
        return TripRepository.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
