package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")
    public void validarIdsDasReservas() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato de retorno da lista de reservas")
    public void garantirContratoListaReserva() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(
                        new File(
                            Utils.getContractsBasePath("booking", "bookings")
                            )
                        )
                    );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato de retorno de uma reserva especifica")
    public void garantirContratoReservaEspecifica() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.specificBooking(primeiroId)
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(
                        new File(
                                Utils.getContractsBasePath("booking", "bookingId")
                        )
                    )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Contract.class)
    @DisplayName("Listar uma reserva específica")
    public void listarUmaReservaEspecifica() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.specificBooking(primeiroId)
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Contract.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro pelo primeiro nome")
    public void listarIdsReservasFiltroFirstName() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        String firstName = getBookingRequest.specificBooking(primeiroId)
                .then()
                .statusCode(200).extract().path("[0].firstname");

        getBookingRequest.specificBookingFirstName(firstName)
                .then()
                .statusCode(200)
                .extract().path("bookingid");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Contract.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro pelo ultimo nome")
    public void listarIdsReservasFiltroLastName() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        String lastName = getBookingRequest.specificBooking(primeiroId)
                .then()
                .statusCode(200).extract().path("[0].lastname");

        getBookingRequest.specificBookingLastName(lastName)
                .then()
                .statusCode(200)
                .extract().path("bookingid");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Contract.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro pelo checkin")
    public void listarIdsReservasFiltroCheckIn() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        String lastName = getBookingRequest.specificBooking(primeiroId)
                .then()
                .statusCode(200).extract().path("[0].lastname");

        getBookingRequest.buscarReservaFiltroCheckIn(lastName)
                .then()
                .statusCode(200)
                .extract().path("bookingid");

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void ValidarErroBuscaComFiltroInvalido() throws Exception {
        int primeiroId = getBookingRequest.allBookings()
                .then()
                .statusCode(200).extract().path("[0].bookingid");

        String firstName = getBookingRequest.specificBooking(primeiroId)
                .then()
                .statusCode(200).extract().path("[0].firstname");

        getBookingRequest.buscaReservaComFiltroInvalido(firstName)
                .then()
                .statusCode(500);

    }
}
