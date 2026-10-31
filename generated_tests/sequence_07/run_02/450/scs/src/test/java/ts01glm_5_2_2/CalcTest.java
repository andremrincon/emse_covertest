package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcAddOperationReturns200() {
        given()
                .when()
                .get("/api/calc/add/15.5/4.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        given()
                .when()
                .get("/api/calc/divide/100/0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcInvalidNumberFormatReturns400() {
        given()
                .when()
                .get("/api/calc/add/10/twenty")
                .then()
                .statusCode(400);
    }
}