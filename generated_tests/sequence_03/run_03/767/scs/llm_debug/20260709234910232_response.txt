package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonth() {
        given()
            .when()
            .get("/api/dateparse/Wednesday/August")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        given()
            .when()
            .get("/api/dateparse/Superday/Movember")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayInvalidMonth() {
        given()
            .when()
            .get("/api/dateparse/tuesday/MAR")
            .then()
            .statusCode(200);
    }
}