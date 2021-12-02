package tripservicekata.trip;

import tripservicekata.exception.DependendClassCallDuringUnitTestException;
import tripservicekata.user.User;

import java.util.List;

public class TripRepository {

    public static List<Trip> findTripsByUser(User user) {
        throw new DependendClassCallDuringUnitTestException(
                "TripRepository should not be invoked on an unit test.");
    }

    public List<Trip> findTripsFor(User user) {
        return user.trips();
    }
}