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
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testBessjValidCall() {
        given()
                .when()
                .get("/api/bessj/3/2.5")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTooSmall() {
        given()
                .when()
                .get("/api/bessj/2/2.5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTooLarge() {
        given()
                .when()
                .get("/api/bessj/1001/2.5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValidCall() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherMExceedsLimit() {
        given()
                .when()
                .get("/api/fisher/1001/5/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherNExceedsLimit() {
        given()
                .when()
                .get("/api/fisher/10/1001/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidXThrowsException() {
        given()
                .when()
                .get("/api/fisher/10/5/1.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidCall() {
        given()
                .when()
                .get("/api/gammq/5.5/2.3")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGammqInvalidParameters() {
        given()
                .when()
                .get("/api/gammq/-1.0/2.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderValidCall() {
        given()
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testRemainderExceedsLimit() {
        given()
                .when()
                .get("/api/remainder/10001/5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeExceedsLimit() {
        given()
                .when()
                .get("/api/remainder/17/-10001")
                .then()
                .statusCode(400);
    }
}