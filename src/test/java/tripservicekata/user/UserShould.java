package tripservicekata.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserShould {

    public User pedro;
    public User maria;

    @BeforeEach
    void setUp() {
        this.pedro = new User();
        this.maria = new User();
    }

    @Test
    void return_false_when_users_are_not_friend() {
        boolean actual = pedro.isFriendWith(maria);

        assertThat(actual).isFalse();
    }

    @Test
    void return_true_when_users_are_friend() {
        pedro.addFriend(maria);

        boolean actual = pedro.isFriendWith(maria);

        assertThat(actual).isTrue();
    }
}