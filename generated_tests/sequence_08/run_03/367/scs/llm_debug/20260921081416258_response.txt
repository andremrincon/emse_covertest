package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_i5_baab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus500_a() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_i700_ababba() {
        given()
            .when()
                .get("/api/costfuns/700/ababba")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus4_abab() {
        given()
            .when()
                .get("/api/costfuns/-4/abab")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_i600_ababba() {
        given()
            .when()
                .get("/api/costfuns/600/ababba")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }
}