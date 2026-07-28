package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testPiOperator() {
        given()
            .when()
                .get("/api/calc/pi/0/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEOperator() {
        given()
            .when()
                .get("/api/calc/e/0/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtOperator() {
        given()
            .when()
                .get("/api/calc/log/1/0")
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
                .statusCode(lessThan(300));
        given()
            .when()
                .get("/api/calc/sqrt/4/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusOperator() {
        given()
            .when()
                .get("/api/calc/subtract/10/3")
            .then()
                .statusCode(lessThan(300));
        given()
            .when()
                .get("/api/calc/multiply/2/3")
            .then()
                .statusCode(lessThan(300));
        given()
            .when()
                .get("/api/calc/plus/5/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideOperator() {
        given()
            .when()
                .get("/api/calc/divide/10/2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOperatorDefaultPath() {
        given()
            .when()
                .get("/api/calc/unknown/1/2")
            .then()
                .statusCode(200);
    }
}