package info.dmerej;

import info.dmerej.mailprovider.SendMailRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailSenderTest {
    @Test
    void should_make_a_valid_http_request() {
        User user1 = new User("name1", "email1");

        FakeHttpClient fakeHttpClient = new FakeHttpClient();

        MailSender mailSender = new MailSender(fakeHttpClient);
        mailSender.sendV1(user1, "Discount !");

        SendMailRequest expectedRequest = new SendMailRequest("email1", "New notification", "Discount !");

        assertTrue(fakeHttpClient.verifyPostedRequest(expectedRequest));
        assertTrue(fakeHttpClient.verifyExpectedNumberOfTries(1));
    }

    @Test
    void should_retry_when_getting_a_503_error() {
        User user1 = new User("name1", "email1");

        FakeHttpClient fakeHttpClient = new FakeHttpClient();

        MailSender mailSender = new MailSender(fakeHttpClient);
        mailSender.sendV2(user1, "Discount !");

        assertTrue(fakeHttpClient.verifyExpectedNumberOfTries(2));

    }
}
