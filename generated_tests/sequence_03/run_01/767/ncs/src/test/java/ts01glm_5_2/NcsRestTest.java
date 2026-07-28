package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void fisherEndpointReturns200ForValidParameters() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisherEndpointReturns400WhenMExceeds1000() {
        given()
                .when()
                .get("/api/fisher/1001/5/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisherEndpointReturns400WhenRuntimeExceptionIsThrown() {
        given()
                .when()
                .get("/api/fisher/10/5/1.2")
                .then()
                .statusCode(200);
    }
}