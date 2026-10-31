package ts01glm_5_2_3;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;

public class DateParseTest {
    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testValidDayAndMonth() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/dateparse/wed/aug")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayValidMonth() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/dateparse/xyz/jan")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayInvalidMonth() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/dateparse/mon/xyz")
        .then()
            .statusCode(200);
    }
}