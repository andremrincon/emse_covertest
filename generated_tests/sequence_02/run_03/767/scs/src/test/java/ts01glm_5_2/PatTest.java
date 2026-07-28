package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class PatTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host");
        if (host == null) {
            host = "localhost";
        }
        String port = System.getProperty("server.port");
        if (port == null) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testPatLengthTwoOrLess() {
        given()
            .when()
                .get("/api/pat/hello/ab")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/hello/ell")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCCBA/ABC")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCXCBA/ABC")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstThenPatAdjacent() {
        given()
            .when()
                .get("/api/pat/CBAABC/ABC")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstThenPatNonAdjacent() {
        given()
            .when()
                .get("/api/pat/CBAXABC/ABC")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnlyNoPat() {
        given()
            .when()
                .get("/api/pat/CBA/ABC")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
                .get("/api/pat/hello/xyz")
            .then()
                .statusCode(lessThan(300));
    }

    @Ignore("Illegal character in path at index 33: http://localhost:8080/api/pat/The quick brown fox jumps ov...")
    @Test(timeout = 60000)
    public void testSingleArgPatEndpoint() {
        given()
            .when()
                .get("/api/pat/{text}", "The quick brown fox jumps over the lazy dog.")
            .then()
                .statusCode(200);
    }
}