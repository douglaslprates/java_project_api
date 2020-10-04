package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.suites.HealthCheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.ping.requests.GetHealthCheckRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class GetHealthCheckTest extends BaseTest {

    GetHealthCheckRequest getHealthCheckRequest = new GetHealthCheckRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(HealthCheck.class)
    @DisplayName("Garantir o retorno do ping")
    public void garantirExameSaude() throws Exception {
        getHealthCheckRequest.ping().then()
                .statusCode(201)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
