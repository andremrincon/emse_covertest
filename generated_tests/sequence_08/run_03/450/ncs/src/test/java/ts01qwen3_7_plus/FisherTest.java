package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisher_m1_n1_x1() {
        given()
            .when()
                .get("/api/fisher/1/1/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m2_n1_x1() {
        given()
            .when()
                .get("/api/fisher/2/1/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m3_n3_xLarge() {
        given()
            .when()
                .get("/api/fisher/3/3/1e15")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m2_n1_xNeg() {
        given()
            .when()
                .get("/api/fisher/2/1/-0.1")
            .then()
                .statusCode(200);
    }
}