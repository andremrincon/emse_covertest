package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIEquals5AndSBaab() {
        given()
            .pathParam("i", "5")
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanNeg444() {
        given()
            .pathParam("i", "-445")
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsNeg333() {
        given()
            .pathParam("i", "-333")
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666() {
        given()
            .pathParam("i", "667")
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsNeg4AndSAbabba() {
        given()
            .pathParam("i", "-4")
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEquals1AndSAbab() {
        given()
            .pathParam("i", "1")
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}