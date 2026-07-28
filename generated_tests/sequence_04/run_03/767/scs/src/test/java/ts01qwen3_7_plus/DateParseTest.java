package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given()
            .baseUri("http://localhost:8080")
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseTueJul() {
        given()
            .baseUri("http://localhost:8080")
            .when()
                .get("/api/dateparse/tue/jul")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedSep() {
        given()
            .baseUri("http://localhost:8080")
            .when()
                .get("/api/dateparse/wed/sep")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurOct() {
        given()
            .baseUri("http://localhost:8080")
            .when()
                .get("/api/dateparse/thur/oct")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriNov() {
        given()
            .baseUri("http://localhost:8080")
            .when()
                .get("/api/dateparse/fri/nov")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSatDec() {
        given()
            .baseUri("http://localhost:8080")
            .when()
                .get("/api/dateparse/sat/dec")
            .then()
                .statusCode(200);
    }
}