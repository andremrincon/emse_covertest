package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testPiAndEConstantOperators() {
        given()
            .when()
                .get("/api/calc/pi/0/0")
            .then()
                .statusCode(200)
                .body(containsString("3.14159"));

        given()
            .when()
                .get("/api/calc/e/0/0")
            .then()
                .statusCode(200)
                .body(containsString("2.71828"));
    }

    @Test(timeout = 60000)
    public void testSqrtAndLogUnaryOperators() {
        given()
            .when()
                .get("/api/calc/sqrt/16/0")
            .then()
                .statusCode(200)
                .body(containsString("4.0"));

        given()
            .when()
                .get("/api/calc/log/1/0")
            .then()
                .statusCode(200)
                .body(containsString("0.0"));
    }

    @Test(timeout = 60000)
    public void testSineAndCosineTrigOperators() {
        given()
            .when()
                .get("/api/calc/sine/0/0")
            .then()
                .statusCode(200)
                .body(containsString("0.0"));

        given()
            .when()
                .get("/api/calc/cosine/0/0")
            .then()
                .statusCode(200)
                .body(containsString("1.0"));
    }

    @Test(timeout = 60000)
    public void testTangentAndPlusOperators() {
        given()
            .when()
                .get("/api/calc/tangent/0/0")
            .then()
                .statusCode(200)
                .body(containsString("0.0"));

        given()
            .when()
                .get("/api/calc/plus/15.5/4.5")
            .then()
                .statusCode(200)
                .body(containsString("20.0"));
    }

    @Test(timeout = 60000)
    public void testSubtractAndMultiplyOperators() {
        given()
            .when()
                .get("/api/calc/subtract/10/3")
            .then()
                .statusCode(200)
                .body(containsString("7.0"));

        given()
            .when()
                .get("/api/calc/multiply/6/7")
            .then()
                .statusCode(200)
                .body(containsString("42.0"));
    }

    @Test(timeout = 60000)
    public void testDivideAndDefaultBranch() {
        given()
            .when()
                .get("/api/calc/divide/20/4")
            .then()
                .statusCode(200)
                .body(containsString("5.0"));

        given()
            .when()
                .get("/api/calc/unknown/1/1")
            .then()
                .statusCode(lessThan(300));
    }
}