package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals5_sGreaterThanABABBA() {
        given()
            .pathParam("i", 5)
            .pathParam("s", "c")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iLessThanNegative444_sEqualsA() {
        given()
            .pathParam("i", -500)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetweenNegative444AndNegative333_sEqualsBAAB() {
        given()
            .pathParam("i", -400)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iGreaterThan666_sEqualsABABBA() {
        given()
            .pathParam("i", 700)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetween555And666_sEqualsA() {
        given()
            .pathParam("i", 600)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsNegative4_sEqualsA() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}