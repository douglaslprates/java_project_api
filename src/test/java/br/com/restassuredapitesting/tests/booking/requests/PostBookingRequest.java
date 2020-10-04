package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    @Step("Criar uma nova reserva")
    public Response criaNovaReserva() {
        JSONObject payload = new JSONObject();
        JSONObject payloadDate = new JSONObject();

        payloadDate.put("checkin", "2020-11-01");
        payloadDate.put("checkout", "2020-12-01");

        payload.put("firstname", "Douglas");
        payload.put("lastname", "Prates");
        payload.put("totalprice", 500);
        payload.put("depositpaid", true);
        payload.put("bookingdates", payloadDate);
        payload.put("additionalneeds", "Breakfast");

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }

    @Step("Validar retorno 500 quando o payload da reserva estiver inválido")
    public Response criaReservaPayloadInvalido() {
        JSONObject payload = new JSONObject();

        payload.put("firstname", "Douglas");
        payload.put("lastname", "Prates");
        payload.put("totalprice", 500);
        payload.put("depositpaid", true);
        payload.put("additionalneeds", "Breakfast");

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }

    @Step("Validar a criação de mais de uma reserva em sequencia")
    public Response criaReservaMaisDeUmaReservaEmSequencia() {
        JSONObject payload = new JSONObject();
        JSONObject payloadDate = new JSONObject();

        payloadDate.put("checkin", "2020-11-01");
        payloadDate.put("checkout", "2020-12-01");

        payload.put("firstname", "Douglas");
        payload.put("lastname", "Prates");
        payload.put("totalprice", 500);
        payload.put("depositpaid", true);
        payload.put("bookingdates", payloadDate);
        payload.put("additionalneeds", "Breakfast");

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }

    @Step("Validar retorno 418 quando o header Accept for invalido")
    public Response criaReservaReservaHeaderInvalido() {
        JSONObject payload = new JSONObject();
        JSONObject payloadDate = new JSONObject();

        payloadDate.put("checkin", "2020-11-01");
        payloadDate.put("checkout", "2020-12-01");

        payload.put("firstname", "Douglas");
        payload.put("lastname", "Prates");
        payload.put("totalprice", 500);
        payload.put("depositpaid", true);
        payload.put("bookingdates", payloadDate);
        payload.put("additionalneeds", "Breakfast");

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "text/html")
                .when()
                .body(payload.toString())
                .post("booking");
    }
}
