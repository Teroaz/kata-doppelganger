package info.dmerej;

import info.dmerej.mailprovider.SendMailRequest;
import info.dmerej.mailprovider.SendMailResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MailSenderTest {

    private final User user = new User("name1", "email1");
    private final SendMailRequest expectedRequest = new SendMailRequest("email1", "New notification", "Discount !");

    @Test
    void should_make_a_valid_http_request() {
        FakeHttpClient fakeHttpClient = new FakeHttpClient();

        MailSender mailSender = new MailSender(fakeHttpClient);
        mailSender.sendV1(user, "Discount !");

        assertTrue(fakeHttpClient.verifyPostedRequest(expectedRequest));
        assertTrue(fakeHttpClient.verifyExpectedNumberOfTries(1));
    }

    @Test
    void should_retry_when_getting_a_503_error() {
        FakeHttpClient fakeHttpClient = new FakeHttpClient();

        MailSender mailSender = new MailSender(fakeHttpClient);
        mailSender.sendV2(user, "Discount !");

        assertTrue(fakeHttpClient.verifyExpectedNumberOfTries(2));
    }

    @Test
    void should_make_a_valid_http_request_mock() {
        HttpClient mockClient = mock(HttpClient.class);
        MailSender mailSender = new MailSender(mockClient);
        mailSender.sendV1(user, "Discount !");

        verify(mockClient).post("https://api.mailprovider.com/v3/", expectedRequest);
    }

    @Test
    void should_retry_when_getting_a_503_error_mock() {
        HttpClient mockClient = mock(HttpClient.class);
        MailSender mailSender = new MailSender(mockClient);
        SendMailResponse response = new SendMailResponse(503, "test");

        when(mockClient.post("https://api.mailprovider.com/v3/", expectedRequest)).thenReturn(response);
        mailSender.sendV2(user, "Discount !");

        verify(mockClient, times(2)).post(anyString(), any());
    }
}
