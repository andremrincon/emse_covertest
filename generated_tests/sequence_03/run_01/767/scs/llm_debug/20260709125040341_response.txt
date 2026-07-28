package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("base.url", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcPlusOperation() {
        given()
            .when()
                .get("/api/calc/plus/15.5/4.5")
            .then()
                .statusCode(200)
                .body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testCalcPiConstantOperation() {
        given()
            .when()
                .get("/api/calc/pi/0/0")
            .then()
                .statusCode(200)
                .body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testCalcSqrtUnaryOperation() {
        given()
            .when()
                .get("/api/calc/sqrt/16/0")
            .then()
                .statusCode(200)
                .body(equalTo("4.0"));
    }
}