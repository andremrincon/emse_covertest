package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectWithIEquals5() {
        given()
            .when()
                .get(baseUrl + "/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithILessThanNegative444() {
        given()
            .when()
                .get(baseUrl + "/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithIBetweenNegative444AndNegative333() {
        given()
            .when()
                .get(baseUrl + "/api/costfuns/-400/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithIEqualsNegative4() {
        given()
            .when()
                .get(baseUrl + "/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithIGreaterThan666AndSEqualsBaab() {
        given()
            .when()
                .get(baseUrl + "/api/costfuns/700/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithSEqualsAbabba() {
        given()
            .when()
                .get(baseUrl + "/api/costfuns/0/ababba")
            .then()
                .statusCode(200);
    }
}