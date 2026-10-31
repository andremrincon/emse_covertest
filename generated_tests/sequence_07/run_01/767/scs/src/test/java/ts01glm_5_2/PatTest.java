package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLenLeq2() {
        given()
            .when()
            .get("/api/pat/hello/ab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherFoundWithPartialMatch() {
        given()
            .when()
            .get("/api/pat/ACXXXXX/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        given()
            .when()
            .get("/api/pat/ABCCDEF/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseImmediate() {
        given()
            .when()
            .get("/api/pat/ABCCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseLater() {
        given()
            .when()
            .get("/api/pat/ZZABCDCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatImmediate() {
        given()
            .when()
            .get("/api/pat/CBAABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatLater() {
        given()
            .when()
            .get("/api/pat/ZZCBAEABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        given()
            .when()
            .get("/api/pat/CBAADEF/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSingleArgPatEndpoint() {
        given()
            .when()
            .get("/api/pat/hello")
            .then()
            .statusCode(200);
    }
}