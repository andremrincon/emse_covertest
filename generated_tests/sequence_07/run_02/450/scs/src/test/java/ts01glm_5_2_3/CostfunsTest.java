package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubject_iEquals5_sEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iEqualsNegative4_sEqualsAbab() {
        given()
            .when()
                .get("/api/costfuns/-4/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iLessThanNegative444_sEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/-500/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iGreaterThan666_sEqualsA() {
        given()
            .when()
                .get("/api/costfuns/700/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetweenNegative444AndNegative333_sEqualsAbab() {
        given()
            .when()
                .get("/api/costfuns/-400/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_iBetween555And666_sEqualsAbab() {
        given()
            .when()
                .get("/api/costfuns/600/abab")
            .then()
                .statusCode(200);
    }
}