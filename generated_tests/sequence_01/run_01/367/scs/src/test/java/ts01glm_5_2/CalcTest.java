package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testCalcPlusBinaryOperator() {
        given()
            .when()
                .get("/api/calc/plus/15.5/4.5")
            .then()
                .statusCode(200)
                .body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testCalcPiConstantOperator() {
        given()
            .when()
                .get("/api/calc/pi/0/0")
            .then()
                .statusCode(200)
                .body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testCalcSqrtUnaryOperator() {
        given()
            .when()
                .get("/api/calc/sqrt/16/0")
            .then()
                .statusCode(200)
                .body(equalTo("4.0"));
    }
}