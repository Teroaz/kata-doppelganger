package info.dmerej;

class FakeAuthorizer implements Authorizer {

    private final boolean shouldAuthorize;

    FakeAuthorizer(boolean shouldAuthorize) {
        this.shouldAuthorize = shouldAuthorize;
    }

    @Override
    public boolean authorize() {
        return shouldAuthorize;
    }
}
