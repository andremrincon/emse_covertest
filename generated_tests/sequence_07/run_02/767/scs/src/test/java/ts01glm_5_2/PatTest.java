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
    public void testPatLenLessOrEqualTwo() {
        given()
            .when()
                .get("/api/pat/hello/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPartialMatchPatNotFound() {
        given()
            .when()
                .get("/api/pat/ABXxyz/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/ABCCXY/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCCBA/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNonAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCDCBA/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatAdjacent() {
        given()
            .when()
                .get("/api/pat/CBAABC/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNonAdjacent() {
        given()
            .when()
                .get("/api/pat/CBADABC/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPatAfter() {
        given()
            .when()
                .get("/api/pat/CBAAXB/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
                .get("/api/pat/xyzxyz/ABC")
            .then()
                .statusCode(200);
    }
}