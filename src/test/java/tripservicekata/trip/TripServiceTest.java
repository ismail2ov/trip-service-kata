package tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Test;
import tripservicekata.exception.UserNotLoggedInException;

class TripServiceTest {

  @Test
  void when_user_is_not_logged_then_throws_exception() {
    TripService tripService = spy(TripService.class);

    doReturn(null).when(tripService).getLoggedUser();

    assertThatExceptionOfType(UserNotLoggedInException.class)
        .isThrownBy(() -> tripService.getTripsByUser(null));
  }

}