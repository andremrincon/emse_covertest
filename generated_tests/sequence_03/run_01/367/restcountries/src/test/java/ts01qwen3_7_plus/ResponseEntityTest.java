package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetStatusFromNameEndpoint404() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageFromCapitalEndpoint404() {
        given()
            .when()
                .get("/v1/capital/123")
            .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusFromRegionEndpoint404() {
        given()
            .when()
                .get("/v1/region/123")
            .then()
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageFromNameEndpoint500() {
        given()
            .when()
                .get("/v1/name/True")
            .then()
                .body("message", equalTo("Not Found"));
    }
}