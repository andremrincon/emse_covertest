package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseValidDayValidMonth() {
        String dayname = "mon";
        String monthname = "jan";

        given()
            .pathParam("dayname", dayname)
            .pathParam("monthname", monthname)
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayInvalidMonth() {
        String dayname = "Wednesday";
        String monthname = "August";

        given()
            .pathParam("dayname", dayname)
            .pathParam("monthname", monthname)
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayValidMonth() {
        String dayname = "tuesday";
        String monthname = "MAR";

        given()
            .pathParam("dayname", dayname)
            .pathParam("monthname", monthname)
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}