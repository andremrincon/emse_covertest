package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjLine43_smallXValue() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/1e-10")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjLine43_zeroXValue() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjLine43_largeNValue() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/999/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLine84_smallMAndN() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/fisher/1/1/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLines92_93_xZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/fisher/10/5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLines92_93_smallNValue() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/fisher/5/1/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLines92_93_largeXValue() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/fisher/10/5/0.99")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqLines112_113_xLessThanAPlusOne() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqLines112_113_xGreaterThanOrEqualAPlusOne() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqLines112_113_smallAValue() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderLine127_negativeA() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/remainder/-9/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderLine127_negativeB() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/remainder/17/-4")
        .then()
            .statusCode(200);
    }
}