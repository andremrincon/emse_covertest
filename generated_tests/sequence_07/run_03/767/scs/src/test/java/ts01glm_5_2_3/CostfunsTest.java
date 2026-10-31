package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIEqualsFive() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEqualsNegativeFour() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSEqualsAbab() {
        given()
            .when()
                .get("/api/costfuns/0/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanNegative444() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666AndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/700/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterEqual555AndSEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/600/ababba")
            .then()
                .statusCode(200);
    }
}