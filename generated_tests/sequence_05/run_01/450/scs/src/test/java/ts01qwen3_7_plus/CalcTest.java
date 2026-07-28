package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testConstantOperations() {
        given()
            .when()
            .get("/api/calc/pi/0/0")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/e/0/0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryOperations() {
        given()
            .when()
            .get("/api/calc/sqrt/16/0")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/log/10/0")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/sine/0/0")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/cosine/0/0")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/tangent/0/0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperations() {
        given()
            .when()
            .get("/api/calc/plus/5/3")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/subtract/10/4")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/multiply/6/7")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/calc/divide/20/4")
            .then()
            .statusCode(200);
    }
}