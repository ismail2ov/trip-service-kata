package tripservicekata.trip;

import org.junit.jupiter.api.Test;
import tripservicekata.user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TripRepositoryShould {

    @Test
    void return_user_trips() {
        TripRepository tripRepository = new TripRepository();
        User user = new User();
        user.addTrip(new Trip());

        List<Trip> actual = tripRepository.findTripsFor(user);

        assertThat(actual).isEqualTo(user.trips());
    }
}