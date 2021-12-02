package tripservicekata.trip;

import org.junit.jupiter.api.Test;
import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

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

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }
    }
}