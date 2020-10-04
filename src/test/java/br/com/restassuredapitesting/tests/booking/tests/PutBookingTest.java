package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token")
    public void validarAlterarUmaReservaUtilizandoToken() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComToken(primeiroId, Utils.validPayloadBooking())
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando Basic auth")
    public void validarAlterarUmaReservaUtilizandoBasicAuth() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComBasicAuth(primeiroId, Utils.validPayloadBooking())
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void validarTentativaAlterarReservaSemToken() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.validarTentativaAlterarReservaSemToken(primeiroId)
                .then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void validarTentativaAlterarReservaTokenInvalido() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.validarTentativaAlterarReservaTokenInvalido(primeiroId)
                .then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void validarTentativaAlterarReservaInexistente() throws Exception {
        int primeiroId = 0;

        putBookingRequest.validarTentativaAlterarReservaInexistente(primeiroId)
                .then()
                .statusCode(400)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
