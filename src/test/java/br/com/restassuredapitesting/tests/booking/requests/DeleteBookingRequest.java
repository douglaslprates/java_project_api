package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.resquests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Buscar uma reserva especifica.")
    public Response deleteReservaComSucesso(int id) {
        return given()
                .header("Accept", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .delete("booking/" + id);
    }

    @Step("Tentar excluir um reserva que não existe.")
    public Response deleteReservaInexistente(int id) {
        return given()
                .header("Accept", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .delete("booking/" + id);
    }

    @Step("Tentar excluir uma reserva sem autorização.")
    public Response deleteReservaSemAutorizacao(int id) {
        return given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .header("Accept", "application/json")
                .when()
                .delete("booking/" + id);
    }
}
