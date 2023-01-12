package tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tripservicekata.exception.UserNotLoggedInException;
import tripservicekata.user.User;

class TripServiceTest {

  private static TripService tripService;

  @BeforeAll
  static void beforeAll() {
    tripService = spy(TripService.class);
  }

  @Test
  void when_user_is_not_logged_then_throws_exception() {
    doReturn(null).when(tripService).getLoggedUser();

    assertThatExceptionOfType(UserNotLoggedInException.class)
        .isThrownBy(() -> tripService.getTripsByUser(null));
  }

  @Test
  @DisplayName("when_user_is_logged_in_and_is_not_a_friend_then_returns_empty_list")
  void when_users_ar_not_friends_then_returns_empty_list() throws UserNotLoggedInException {
    User loggedUser = mock(User.class);
    User friendUser = mock(User.class);
    doReturn(loggedUser).when(tripService).getLoggedUser();

    List<Trip> actual = tripService.getTripsByUser(friendUser);

    assertThat(actual).isEmpty();
  }

  @Test
  void when_users_are_friends_then_returns_list_with_trips() throws UserNotLoggedInException {
    User loggedUser = mock(User.class);
    User friendUser = mock(User.class);
    doReturn(loggedUser).when(tripService).getLoggedUser();
    doReturn(List.of(loggedUser)).when(friendUser).getFriends();
    Trip trip1 = mock(Trip.class);
    Trip trip2 = mock(Trip.class);

    doReturn(List.of(trip1, trip2)).when(tripService).getTripsFor(friendUser);

    List<Trip> actual = tripService.getTripsByUser(friendUser);

    assertThat(actual).containsExactly(trip1, trip2);
  }
}