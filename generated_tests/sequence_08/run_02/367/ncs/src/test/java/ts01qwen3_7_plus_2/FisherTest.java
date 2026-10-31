package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisher_a1_b1() {
        given()
            .when()
                .get("http://localhost:8080/api/fisher/1/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b2() {
        given()
            .when()
                .get("http://localhost:8080/api/fisher/1/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a2_b1() {
        given()
            .when()
                .get("http://localhost:8080/api/fisher/2/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a2_b2() {
        given()
            .when()
                .get("http://localhost:8080/api/fisher/2/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_p_lt_0() {
        given()
            .when()
                .get("http://localhost:8080/api/fisher/1/1/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_p_gt_1() {
        given()
            .when()
                .get("http://localhost:8080/api/fisher/1/1/100.0")
            .then()
                .statusCode(200);
    }
}