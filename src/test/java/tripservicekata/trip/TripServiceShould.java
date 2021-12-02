package tripservicekata.trip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TripServiceShould {

    public static final Trip TRIP_TO_IBIZA = new Trip();
    public static final Trip TRIP_TO_MALLORCA = new Trip();

    private User loggedUser;
    private User friend;

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;



    @BeforeEach
    void setUp() {
        this.friend = new User();
    }

    @Test
    void throw_an_exception_when_user_is_not_logged_in() {
        loggedUser = null;

        assertThatThrownBy(() -> tripService.getTripsByUser(friend, loggedUser)).isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    void return_empty_trips_list_when_users_are_not_friends() throws UserNotLoggedInException {
        loggedUser = new User();

        List<Trip> actual = tripService.getTripsByUser(friend, loggedUser);

        assertThat(actual).isEmpty();
    }

    @Test
    void return_trips_list_when_users_are_friends() throws UserNotLoggedInException {
        loggedUser = new User();
        User anotherUser = new User();

        friend.addFriend(anotherUser);
        friend.addFriend(loggedUser);
        friend.addTrip(TRIP_TO_IBIZA);
        friend.addTrip(TRIP_TO_MALLORCA);

        when(tripRepository.findTripsFor(friend)).thenReturn(friend.trips());

        List<Trip> actual = tripService.getTripsByUser(friend, loggedUser);

        assertThat(actual).containsExactly(TRIP_TO_IBIZA, TRIP_TO_MALLORCA);
    }
}