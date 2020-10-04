package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.resquests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;


public class PutBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Alterar uma reserva com token")
    public Response alterarUmaReservaComToken(int id, JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva utilizando o Basic auth")
    public Response alterarUmaReservaComBasicAuth(int id, JSONObject payload) {
        return given()
                .auth()
                .preemptive()
                .basic("admin", "password123")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Tentar alterar uma reserva quando o token não for enviado")
    public Response validarTentativaAlterarReservaSemToken(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .put("booking/" + id);
    }

    @Step("Tentar alterar uma reserva quando o token enviado for inválido")
    public Response validarTentativaAlterarReservaTokenInvalido(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "0000000")
                .when()
                .put("booking/" + id);
    }

    @Step("Tentar alterar uma reserva que não existe")
    public Response validarTentativaAlterarReservaInexistente(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .put("booking/0" + id);
    }
}
