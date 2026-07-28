package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class DateParseTest {

    private final String baseUrl = System.getProperty("test.base.url", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testDateParseJun() {
        given()
            .baseUri(baseUrl)
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jun")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseJul() {
        given()
            .baseUri(baseUrl)
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jul")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSep() {
        given()
            .baseUri(baseUrl)
            .pathParam("dayname", "mon")
            .pathParam("monthname", "sep")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseOct() {
        given()
            .baseUri(baseUrl)
            .pathParam("dayname", "mon")
            .pathParam("monthname", "oct")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseNov() {
        given()
            .baseUri(baseUrl)
            .pathParam("dayname", "mon")
            .pathParam("monthname", "nov")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}