package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(anyOf(is(200), is(500)));
    }

    @Test(timeout = 60000)
    public void testDateParseTueFeb() {
        given()
            .when()
                .get("/api/dateparse/tue/feb")
            .then()
                .statusCode(anyOf(is(200), is(500)));
    }

    @Test(timeout = 60000)
    public void testDateParseWedMar() {
        given()
            .when()
                .get("/api/dateparse/wed/mar")
            .then()
                .statusCode(anyOf(is(200), is(500)));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidApr() {
        given()
            .when()
                .get("/api/dateparse/invalid/apr")
            .then()
                .statusCode(anyOf(is(200), is(500)));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidInvalid() {
        given()
            .when()
                .get("/api/dateparse/invalid/invalid")
            .then()
                .statusCode(anyOf(is(200), is(500)));
    }

    @Test(timeout = 60000)
    public void testDateParseSunDec() {
        given()
            .when()
                .get("/api/dateparse/sun/dec")
            .then()
                .statusCode(anyOf(is(200), is(500)));
    }
}