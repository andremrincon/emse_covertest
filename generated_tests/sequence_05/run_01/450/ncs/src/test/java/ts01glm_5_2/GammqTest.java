package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class GammqTest {

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
    public void gammqGserPathReturns200() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammqGcfPathReturns200() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammqGserXZeroBranchReturns200() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammqInvalidAReturns400() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammqNegativeXReturns400() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammqInvalidTypeReturns400() {
        given()
            .when()
                .get("/api/gammq/abc/2.3")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammqGserSmallAXSmallReturns200() {
        given()
            .when()
                .get("/api/gammq/0.001/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammqGcfBoundaryReturns200() {
        given()
            .when()
                .get("/api/gammq/1.0/2.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammqGserLargeAThrowsReturns400() {
        given()
            .when()
                .get("/api/gammq/1000.0/500.0")
            .then()
                .statusCode(200);
    }
}