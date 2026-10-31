package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanNegative444() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/700/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/1/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/1/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsNegative4() {
        given()
            .when()
                .get("/api/costfuns/-4/abab")
            .then()
                .statusCode(200);
    }
}