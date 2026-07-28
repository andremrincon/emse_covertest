package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("port", "8080"));
    }

    @Test(timeout = 60000)
    public void testPatlenLessThanOrEqual2() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "abc", "ab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "xyz", "abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "ABCdef", "ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacent() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "ABCCBA", "ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNonAdjacentWithFalsePossmatch() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "ABCCBXefCBA", "ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatAdjacent() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "CBAABC", "ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatNonAdjacentWithFalsePossmatch() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "CBAABXefABC", "ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "CBAdef", "ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPossmatchFalseForBothPatAndPatrev() {
        given()
            .when()
            .get("/api/pat/{txt}/{pat}", "ABXCBXdef", "ABC")
            .then()
            .statusCode(200);
    }
}