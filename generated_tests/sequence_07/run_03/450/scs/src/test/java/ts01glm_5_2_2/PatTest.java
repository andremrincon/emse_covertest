package ts01glm_5_2_2;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {
    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLenNotGreaterThanTwo() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abc/ab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcdef/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseImmediatelyAfter() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseLater() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcXcba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatImmediatelyAfter() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatLater() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaXabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaXYZ/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/xyzxyz/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundAtEndInnerLoopNotExecuted() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/XYZabc/abc")
        .then()
            .statusCode(200);
    }
}