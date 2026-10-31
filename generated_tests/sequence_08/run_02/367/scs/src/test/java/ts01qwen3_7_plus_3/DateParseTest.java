package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class DateParseTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testDateParseSun() {
        given()
            .pathParam("dayname", "sun")
            .pathParam("monthname", "jan")
        .when()
            .get(BASE_URL + "/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseJun() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jun")
        .when()
            .get(BASE_URL + "/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseJul() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jul")
        .when()
            .get(BASE_URL + "/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseAug() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "aug")
        .when()
            .get(BASE_URL + "/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSep() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "sep")
        .when()
            .get(BASE_URL + "/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}