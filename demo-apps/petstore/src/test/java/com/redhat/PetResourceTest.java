package com.redhat;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PetResourceTest {

    @Test
    public void testGetPetsEndpoint() {
        given()
          .when()
            .get("/api/pets")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCreatePetEndpoint() {
        given()
          .when()
            .contentType("application/json")
            .accept("text/plain")
            .body("{\"id\": 1, \"name\": \"Dog\"}")
            .post("/api/pets")
          .then()
             .statusCode(200);
    }

    @Test
    public void testDeletePetEndpoint() {
        given()
          .when()
            .contentType("application/json")
            .accept("text/plain")
            .delete("/api/pets/1")
          .then()
             .statusCode(200);
    }

}
