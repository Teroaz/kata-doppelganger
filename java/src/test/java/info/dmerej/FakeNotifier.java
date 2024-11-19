package info.dmerej;

import java.util.ArrayList;
import java.util.List;

public class FakeNotifier implements Notifier {

    int numberOfNotifications;
    List<User> usersNotified = new ArrayList<>();

    @Override
    public void notify(User user, String message) {
        numberOfNotifications++;
        usersNotified.add(user);
        // send a notification
    }

    public boolean verifyExpectedNumberOfNotifications(int expected) {
        return expected == numberOfNotifications;
    }

    public boolean verifyUserNotified(User userCalled) {
        for (User user : usersNotified) {
            if (user.name().equals(userCalled.name())) {
                return true;
            }
        }

        return false;
    }
}
