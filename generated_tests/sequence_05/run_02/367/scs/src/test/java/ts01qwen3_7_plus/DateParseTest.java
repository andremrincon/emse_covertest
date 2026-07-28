package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jan")
        .when()
            .get("http://localhost:8080/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testDateParseTueFeb() {
        given()
            .pathParam("dayname", "tue")
            .pathParam("monthname", "feb")
        .when()
            .get("http://localhost:8080/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testDateParseWedMar() {
        given()
            .pathParam("dayname", "wed")
            .pathParam("monthname", "mar")
        .when()
            .get("http://localhost:8080/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testDateParseThurApr() {
        given()
            .pathParam("dayname", "thur")
            .pathParam("monthname", "apr")
        .when()
            .get("http://localhost:8080/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200)
            .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testDateParseFriMay() {
        given()
            .pathParam("dayname", "fri")
            .pathParam("monthname", "may")
        .when()
            .get("http://localhost:8080/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200)
            .body(equalTo("6"));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalid() {
        given()
            .pathParam("dayname", "xyz")
            .pathParam("monthname", "xyz")
        .when()
            .get("http://localhost:8080/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}