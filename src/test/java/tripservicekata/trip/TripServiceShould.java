package tripservicekata.trip;

import org.junit.jupiter.api.Test;
import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

    public static final Trip TRIP_TO_IBIZA = new Trip();
    public static final Trip TRIP_TO_MALLORCA = new Trip();

    private User loggedUser;

    @Test
    void throw_an_exception_when_user_is_not_logged_in() {
        TripService tripService = new TestableTripService();
        loggedUser = null;

        assertThatThrownBy(() -> tripService.getTripsByUser(loggedUser)).isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    void return_empty_trips_list_when_user_is_logged_in() throws UserNotLoggedInException {
        TripService tripService = new TestableTripService();
        loggedUser = new User();

        List<Trip> actual = tripService.getTripsByUser(loggedUser);

        assertThat(actual).isEmpty();
    }

    @Test
    void return_trips_list_when_users_are_friends() throws UserNotLoggedInException {
        TripService tripService = new TestableTripService();
        loggedUser = new User();

        User friend = new User();
        friend.addFriend(new User());
        friend.addFriend(loggedUser);
        friend.addTrip(TRIP_TO_IBIZA);
        friend.addTrip(TRIP_TO_MALLORCA);

        List<Trip> actual = tripService.getTripsByUser(friend);

        assertThat(actual).containsExactly(TRIP_TO_IBIZA, TRIP_TO_MALLORCA);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> getTripsFor(User user) {
            return user.trips();
        }
    }
}