package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonth() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        given()
            .baseUri(baseUrl)
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jan")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        given()
            .baseUri(baseUrl)
            .pathParam("dayname", "xyz")
            .pathParam("monthname", "xyz")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}