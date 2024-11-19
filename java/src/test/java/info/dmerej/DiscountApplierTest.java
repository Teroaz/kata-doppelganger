package info.dmerej;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DiscountApplierTest {

    private final List<User> usersList = new ArrayList<>();
    private final int discount = 10;

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

        discountApplier.applyV1(discount, usersList);
        assertTrue(notifier.verifyExpectedNumberOfNotifications(2));
    }

    @Test
    void should_notify_twice_when_applying_discount_for_two_users_v2() {
        FakeNotifier notifier = new FakeNotifier();
        DiscountApplier discountApplier = new DiscountApplier(notifier);

        discountApplier.applyV2(discount, usersList);

        for (User user : usersList) {
            assertTrue(notifier.verifyUserNotified(user));
        }
    }

    @Test
    void should_notify_twice_when_applying_discount_for_two_users_v1_mock() {
        Notifier mockNotifier = mock(Notifier.class);
        DiscountApplier discountApplier = new DiscountApplier(mockNotifier);
        discountApplier.applyV1(discount, usersList);

        verify(mockNotifier, times(2)).notify(any(User.class), anyString());
    }

    @Test
    void should_notify_twice_when_applying_discount_for_two_users_v2_mock() {
        Notifier mockNotifier = mock(Notifier.class);
        DiscountApplier discountApplier = new DiscountApplier(mockNotifier);
        discountApplier.applyV2(discount, usersList);

        verify(mockNotifier, times(1)).notify(usersList.get(0), "You've got a new discount of 10%");
        verify(mockNotifier, times(1)).notify(usersList.get(1), "You've got a new discount of 10%");
    }

}
