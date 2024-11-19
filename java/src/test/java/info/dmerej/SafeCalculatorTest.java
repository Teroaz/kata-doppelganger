package info.dmerej;

import org.junit.jupiter.api.Test;

public class SafeCalculatorTest {
  @Test
  void should_not_throw_when_authorized() {
    Authorizer authorizer = new FakeAuthorizer(true);
    SafeCalculator safeCalculator = new SafeCalculator(authorizer);
    safeCalculator.add(10, 23);
  }
}
