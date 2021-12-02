package tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserShould {

    @Test
    void return_false_when_users_are_not_friend() {
        User pedro = new User();
        User maria = new User();

        boolean actual = pedro.isFriendWith(maria);

        assertThat(actual).isFalse();
    }
}