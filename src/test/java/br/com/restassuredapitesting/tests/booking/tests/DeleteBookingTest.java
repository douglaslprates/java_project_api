package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class DeleteBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Deletar uma reserva")
    public void validarDeleteReservaComSucesso() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        deleteBookingRequest.deleteReservaComSucesso(primeiroId)
                .then()
                .statusCode(201)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir um reserva que não existe")
    public void validarDeleteReservaInexistente() throws Exception {
        int primeiroId = 0;

        deleteBookingRequest.deleteReservaComSucesso(primeiroId)
                .then()
                .statusCode(405)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir um reserva que não existe")
    public void validarDeleteReservaSemAutorizacao() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        deleteBookingRequest.deleteReservaSemAutorizacao(primeiroId)
                .then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }
}
