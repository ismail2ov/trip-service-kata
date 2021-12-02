package tripservicekata.trip;

import lombok.RequiredArgsConstructor;
import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        validateIfUserIsAuthenticated(loggedUser);

        return getTripsFor(user, loggedUser);
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

    private List<Trip> getTripsFor(User user) {
        return tripRepository.findTripsFor(user);
    }
}
