package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testFisherSuccessPath() {
        given()
                .pathParam("m", 10)
                .pathParam("n", 5)
                .pathParam("x", 0.75)
                .when()
                .get("/api/fisher/{m}/{n}/{x}")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherExceptionPath() {
        given()
                .pathParam("m", 12)
                .pathParam("n", 6)
                .pathParam("x", 1.2)
                .when()
                .get("/api/fisher/{m}/{n}/{x}")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherGuardCheckExceedsLimit() {
        given()
                .pathParam("m", 1001)
                .pathParam("n", 5)
                .pathParam("x", 0.5)
                .when()
                .get("/api/fisher/{m}/{n}/{x}")
                .then()
                .statusCode(400);
    }
}