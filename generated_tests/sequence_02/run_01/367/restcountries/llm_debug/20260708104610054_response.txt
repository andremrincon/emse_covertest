package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void v1NameNotFoundReturnsResponseEntityWithStatus() {
        given()
                .pathParam("name", "123")
        .when()
                .get("/v1/name/{name}")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1CapitalNotFoundReturnsResponseEntityWithMessage() {
        given()
                .pathParam("capital", "123")
        .when()
                .get("/v1/capital/{capital}")
        .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void v1RegionNotFoundReturnsResponseEntityWithStatus() {
        given()
                .pathParam("region", "123")
        .when()
                .get("/v1/region/{region}")
        .then()
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void v2NameNotFoundReturnsResponseEntityWithMessage() {
        given()
                .pathParam("name", "123")
        .when()
                .get("/v2/name/{name}")
        .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void v2CurrencyBadRequestReturnsResponseEntityWithStatus() {
        given()
                .pathParam("currency", "123")
        .when()
                .get("/v2/currency/{currency}")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v2RegionalblocNotFoundReturnsResponseEntityWithStatus() {
        given()
                .pathParam("regionalbloc", "123")
        .when()
                .get("/v2/regionalbloc/{regionalbloc}")
        .then()
                .body("status", equalTo(404));
    }
}