package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("API_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testConstantOperatorsPiAndE() {
        given()
            .when()
                .get("/api/calc/PI/0/0")
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
    public void testUnaryMathOperatorsSqrtAndLog() {
        given()
            .when()
                .get("/api/calc/sqrt/16/0")
            .then()
                .statusCode(200)
                .body(equalTo("4.0"));

        given()
            .when()
                .get("/api/calc/log/1/0")
            .then()
                .statusCode(200)
                .body(equalTo("0.0"));
    }

    @Test(timeout = 60000)
    public void testTrigonometricOperators() {
        given()
            .when()
                .get("/api/calc/sine/0/0")
            .then()
                .statusCode(200)
                .body(equalTo("0.0"));

        given()
            .when()
                .get("/api/calc/cosine/0/0")
            .then()
                .statusCode(200)
                .body(equalTo("1.0"));

        given()
            .when()
                .get("/api/calc/tangent/0/0")
            .then()
                .statusCode(200)
                .body(equalTo("0.0"));
    }

    @Test(timeout = 60000)
    public void testBinaryAdditiveOperators() {
        given()
            .when()
                .get("/api/calc/plus/1/2")
            .then()
                .statusCode(200)
                .body(equalTo("3.0"));

        given()
            .when()
                .get("/api/calc/subtract/5/3")
            .then()
                .statusCode(200)
                .body(equalTo("2.0"));
    }

    @Test(timeout = 60000)
    public void testBinaryMultiplicativeOperators() {
        given()
            .when()
                .get("/api/calc/multiply/3/4")
            .then()
                .statusCode(200)
                .body(equalTo("12.0"));

        given()
            .when()
                .get("/api/calc/divide/10/2")
            .then()
                .statusCode(200)
                .body(equalTo("5.0"));
    }

    @Test(timeout = 60000)
    public void testUnknownOperatorDefaultBranch() {
        given()
            .when()
                .get("/api/calc/unknown/1/2")
            .then()
                .statusCode(200)
                .body(equalTo("0.0"));
    }
}