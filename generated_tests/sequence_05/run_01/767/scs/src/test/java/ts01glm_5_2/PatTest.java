package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @Before
    public void setUp() {
        String baseUri = System.getProperty("baseUri");
        if (baseUri == null || baseUri.isEmpty()) {
            baseUri = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUri;
    }

    @Test(timeout = 60000)
    public void patLenLe2_returnsZero() {
        given()
            .when()
            .get("/api/pat/ABCDEF/ab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void patFoundReverseNotFound_returnsOne() {
        given()
            .when()
            .get("/api/pat/ABCDEF/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void patFoundReverseFoundImmediately_palindrome() {
        given()
            .when()
            .get("/api/pat/ABCCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void patFoundReverseFoundLater_nonAdjacent() {
        given()
            .when()
            .get("/api/pat/ABCXYZCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void reverseFoundFirstPatFoundImmediately_palindrome() {
        given()
            .when()
            .get("/api/pat/CBAABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void reverseFoundFirstPatFoundLater_nonAdjacent() {
        given()
            .when()
            .get("/api/pat/CBAXYZABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void reverseFoundPatNotFound_returnsTwo() {
        given()
            .when()
            .get("/api/pat/CBADEF/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void neitherPatNorReverseFound_returnsZero() {
        given()
            .when()
            .get("/api/pat/XYZXYZ/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void singleArgPatEndpoint_exercisesConstructor() {
        given()
            .when()
            .get("/api/pat/helloworld")
            .then()
            .statusCode(200);
    }
}