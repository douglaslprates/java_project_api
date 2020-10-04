package br.com.restassuredapitesting.tests.ping.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetHealthCheckRequest {
    @Step("Buscar resposta do ping")
    public Response ping() {
        return given()
                .when()
                .get("ping");
    }
}
