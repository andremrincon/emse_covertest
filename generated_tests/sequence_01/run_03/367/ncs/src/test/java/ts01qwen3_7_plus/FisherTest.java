package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven() {
        given()
            .when()
                .get("/api/fisher/1/2/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd() {
        given()
            .when()
                .get("/api/fisher/2/1/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pGreaterThanOne() {
        given()
            .when()
                .get("/api/fisher/2/2/10000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pInRange() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pLessThanZero() {
        given()
            .when()
                .get("/api/fisher/3/2/-1.0")
            .then()
                .statusCode(200);
    }
}