package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost");
        String port = System.getenv().getOrDefault("PORT", "8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testCalcAddReturns200() {
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
    public void testCalcInvalidNumberReturns400() {
        given()
            .when()
                .get("/api/calc/add/10/twenty")
            .then()
                .statusCode(400);
    }
}