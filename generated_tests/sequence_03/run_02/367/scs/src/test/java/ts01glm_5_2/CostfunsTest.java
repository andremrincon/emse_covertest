package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals5() {
        given()
            .when()
                .get("/api/costfuns/5/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iLessThanNegative444() {
        given()
            .when()
                .get("/api/costfuns/-445/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsNegative333() {
        given()
            .when()
                .get("/api/costfuns/-333/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/667/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsNegative4_sEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-4/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals1_sEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/1/ababba")
            .then()
                .statusCode(200);
    }
}