package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetMessageViaNameEndpoint() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusViaCapitalEndpoint() {
        given()
            .pathParam("capital", "123")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testPostV1EndpointMethodNotAllowed() {
        given()
        .when()
            .post("/v1")
        .then()
            .statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetStatusViaRegionEndpoint() {
        given()
            .pathParam("region", "123")
        .when()
            .get("/v1/region/{region}")
        .then()
            .body("status", equalTo(404));
    }
}