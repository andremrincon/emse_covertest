package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIEqualsFiveBranch() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanNegative444Branch() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsNegativeFourAndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-4/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666AndSEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/700/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIBetween555And666WithSGreaterThanAbabba() {
        given()
            .when()
                .get("/api/costfuns/600/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIBetweenNegative444And333WithSLessThanAbabba() {
        given()
            .when()
                .get("/api/costfuns/-350/a")
            .then()
                .statusCode(200);
    }
}