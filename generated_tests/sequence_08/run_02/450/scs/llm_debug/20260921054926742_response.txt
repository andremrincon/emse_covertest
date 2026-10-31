package ts01qwen3_7_plus_3;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseTueApr() {
        given()
            .when()
                .get("/api/dateparse/tue/apr")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseWedDec() {
        given()
            .when()
                .get("/api/dateparse/wed/dec")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalid() {
        given()
            .when()
                .get("/api/dateparse/123/456")
            .then()
                .statusCode(200);
    }
}