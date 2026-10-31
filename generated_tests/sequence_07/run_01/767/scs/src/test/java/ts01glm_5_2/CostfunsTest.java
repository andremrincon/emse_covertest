package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals5() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iLessThanNeg444() {
        given()
            .when()
                .get("/api/costfuns/-445/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsNeg333() {
        given()
            .when()
                .get("/api/costfuns/-333/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iGreaterThan666_sEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/667/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsNeg4_sEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/-4/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals555() {
        given()
            .when()
                .get("/api/costfuns/555/a")
            .then()
                .statusCode(200);
    }
}