package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValidAcronym() {
        given()
            .pathParam("regionalbloc", "EU")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValidOtherAcronym() {
        given()
            .pathParam("regionalbloc", "NAFTA")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocInvalid() {
        given()
            .pathParam("regionalbloc", "123")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(404);
    }
}