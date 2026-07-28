package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Test(timeout = 60000)
    public void testCostfuns_I5_Sbaab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Iminus500_Sabab() {
        given()
            .when()
                .get("/api/costfuns/-500/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Iminus400_Sababba() {
        given()
            .when()
                .get("/api/costfuns/-400/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_I700_Sababbc() {
        given()
            .when()
                .get("/api/costfuns/700/ababbc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Iminus4_Sabab() {
        given()
            .when()
                .get("/api/costfuns/-4/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_I600_Sxyz() {
        given()
            .when()
                .get("/api/costfuns/600/xyz")
            .then()
                .statusCode(200);
    }
}