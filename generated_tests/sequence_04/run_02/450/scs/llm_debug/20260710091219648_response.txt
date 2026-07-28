package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testCalcPlusReturns200() {
        given()
            .when()
                .get("/api/calc/plus/15.5/4.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcInvalidArgReturns400() {
        given()
            .when()
                .get("/api/calc/add/10/twenty")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        given()
            .when()
                .get("/api/calc/divide/100/0")
            .then()
                .statusCode(200);
    }
}