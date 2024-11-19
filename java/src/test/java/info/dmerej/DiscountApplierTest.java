package info.dmerej;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscountApplierTest {

    private final List<User> usersList = new ArrayList<>();

    DiscountApplierTest() {
        User user1 = new User("name1", "email1");
        User user2 = new User("name2", "email2");
        usersList.add(user1);
        usersList.add(user2);
    }

    @Test
    void should_notify_twice_when_applying_discount_for_two_users_v1() {
        FakeNotifier notifier = new FakeNotifier();
        DiscountApplier discountApplier = new DiscountApplier(notifier);

        discountApplier.applyV1(10, usersList);
        assertTrue(notifier.verifyExpectedNumberOfNotifications(2));
    }

    @Test
    void should_notify_twice_when_applying_discount_for_two_users_v2() {
        FakeNotifier notifier = new FakeNotifier();
        DiscountApplier discountApplier = new DiscountApplier(notifier);

        discountApplier.applyV2(0, usersList);

        for (User user : usersList) {
            assertTrue(notifier.verifyUserNotified(user));
        }
    }

}
