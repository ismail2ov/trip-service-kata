package tripservicekata.trip;

import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;
import tripservicekata.user.UserSession;

import java.util.Collections;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        validateIfUserIsAuthenticated(getLoggedUser());

        return getTripsFor(user, getLoggedUser());
    }

    private List<Trip> getTripsFor(User user, User loggedUser) {
        if (user.isFriendWith(loggedUser)) {
            return getTripsFor(user);
        }
        return Collections.emptyList();
    }

    private void validateIfUserIsAuthenticated(User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> getTripsFor(User user) {
        return TripRepository.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
