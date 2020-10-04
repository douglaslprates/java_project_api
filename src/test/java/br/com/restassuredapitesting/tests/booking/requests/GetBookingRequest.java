package br.com.restassuredapitesting.tests.booking.requests;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas as reservas")
    public Response allBookings() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }

    @Step("Buscar uma reserva especifica")
    public Response specificBooking(int id) {
        return given()
                .header("Accept", "application/json")
                .when()
                .get("booking/" + id);
    }

    @Step("Buscar uma reserva pelo filtro de primeiro nome")
    public Response specificBookingFirstName(String firstName) {
        return given()
                .header("Accept", "application/json")
                .params("firstname", "")
                .when()
                .get("booking?" + firstName);
    }

    @Step("Buscar uma reserva pelo filtro de ultimo nome")
    public Response specificBookingLastName(String lastName) {
        return given()
                .header("Accept", "application/json")
                .params("lastname", "")
                .when()
                .get("booking?" + lastName);
    }

    @Step("Buscar uma reserva pelo filtro de checkin.")
    public Response buscarReservaFiltroCheckIn(String checkin) {
        return given()
                .header("Accept", "application/json")
                .params("checkin", "")
                .when()
                .get("booking?" + checkin);
    }

    @Step("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public Response buscaReservaComFiltroInvalido(String checkin) {
                return given()
                .header("Accept", "application/json")
                .when()
                .get("11?" + checkin);
    }
}
