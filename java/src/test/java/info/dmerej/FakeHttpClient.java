package info.dmerej;

import info.dmerej.mailprovider.SendMailRequest;
import info.dmerej.mailprovider.SendMailResponse;

public class FakeHttpClient implements HttpClient {

    SendMailRequest postedRequest;
    int numberOfTries;

    @Override
    public SendMailResponse post(String url, Object request) {
        postedRequest = (SendMailRequest) request;
        SendMailResponse response = new SendMailResponse(503, "Server OFF");
        numberOfTries++;
        return response;
    }

    boolean verifyPostedRequest(SendMailRequest request) {
        return request.equals(postedRequest);
    }

    boolean verifyExpectedNumberOfTries(int expected) {
        return numberOfTries == expected;
    }

}
