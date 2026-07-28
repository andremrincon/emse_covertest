package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusFromNameEndpoint() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageFromRegionEndpoint() {
        given()
            .when()
                .get("/v1/region/123")
            .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusFromPostEndpoint() {
        given()
            .when()
                .post("/v1")
            .then()
                .statusCode(405);
    }
}