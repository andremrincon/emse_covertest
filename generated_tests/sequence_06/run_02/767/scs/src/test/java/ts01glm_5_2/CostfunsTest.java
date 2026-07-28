package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIEqualsFiveAndSEqualsBaab() {
        given()
            .pathParam("i", "5")
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanNegative444AndSEqualsAbabba() {
        given()
            .pathParam("i", "-500")
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666AndSGreaterThanAbabba() {
        given()
            .pathParam("i", "700")
            .pathParam("s", "zzzzz")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsNegativeFour() {
        given()
            .pathParam("i", "-4")
            .pathParam("s", "aaaa")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsZeroAndSEqualsA() {
        given()
            .pathParam("i", "0")
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidIntegerParameter() {
        given()
            .pathParam("i", "one")
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(400);
    }
}