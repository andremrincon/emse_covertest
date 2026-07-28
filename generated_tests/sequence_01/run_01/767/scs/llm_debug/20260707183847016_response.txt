package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void dateParse_validDayAndMonth_returns200() {
        given()
            .when()
                .get("/api/dateparse/Wednesday/August")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void dateParse_invalidDayName_returns500() {
        given()
            .when()
                .get("/api/dateparse/Superday/Movember")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void dateparse_numericInputs_returns500() {
        given()
            .when()
                .get("/api/dateparse/123/456")
            .then()
                .statusCode(200);
    }
}