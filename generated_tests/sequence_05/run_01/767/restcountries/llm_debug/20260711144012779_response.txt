package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocSuccess() {
        given()
            .pathParam("regionalbloc", "EU")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
            .pathParam("regionalbloc", "123")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(404);
    }
}