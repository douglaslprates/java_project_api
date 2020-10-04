package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void validarCriarUmaNovaReserva() throws Exception {
        postBookingRequest.criaNovaReserva().then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma nova reserva")
    public void validarCriarUmaNovaReservaPayloadInvalido() throws Exception {
        postBookingRequest.criaReservaPayloadInvalido()
                .then()
                .statusCode(500)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma nova reserva")
    public void validarCriarNovaReservaHeaderInvalido() throws Exception {
        postBookingRequest.criaReservaReservaHeaderInvalido()
                .then()
                .statusCode(418)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }
}
