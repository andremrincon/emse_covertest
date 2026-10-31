package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatLenLessOrEqualTwo() {
        given()
            .when()
            .get("/api/pat/abc/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverseMatch() {
        given()
            .when()
            .get("/api/pat/ABCCDEF/ABC")
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
            .get("/api/pat/ABCXCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatAdjacent() {
        given()
            .when()
            .get("/api/pat/CBAABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatNonAdjacent() {
        given()
            .when()
            .get("/api/pat/CBAXABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPatMatch() {
        given()
            .when()
            .get("/api/pat/CBAAXYZ/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNothingFound() {
        given()
            .when()
            .get("/api/pat/XYZXYZ/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSingleArgEndpoint() {
        given()
            .when()
            .get("/api/pat/abc")
            .then()
            .statusCode(lessThan(300));
    }
}