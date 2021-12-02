package tripservicekata.trip;

import org.junit.jupiter.api.Test;
import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceShould {

    @Test
    void throw_an_exception_when_user_is_not_logged_in() {
        TripService tripService = new TestableTripService();

        assertThatThrownBy(() -> tripService.getTripsByUser(null)).isInstanceOf(UserNotLoggedInException.class);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}