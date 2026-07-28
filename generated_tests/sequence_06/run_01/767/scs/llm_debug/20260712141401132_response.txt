package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testValidDaynameValidMonthname() {
        String dayname = "mon";
        String monthname = "jan";

        given()
            .pathParam("dayname", dayname)
            .pathParam("monthname", monthname)
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testValidDaynameDecMonthname() {
        String dayname = "tue";
        String monthname = "dec";

        given()
            .pathParam("dayname", dayname)
            .pathParam("monthname", monthname)
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidDaynameValidMonthname() {
        String dayname = "xyz";
        String monthname = "feb";

        given()
            .pathParam("dayname", dayname)
            .pathParam("monthname", monthname)
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDaynameInvalidMonthname() {
        String dayname = "abc";
        String monthname = "xyz";

        given()
            .pathParam("dayname", dayname)
            .pathParam("monthname", monthname)
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}