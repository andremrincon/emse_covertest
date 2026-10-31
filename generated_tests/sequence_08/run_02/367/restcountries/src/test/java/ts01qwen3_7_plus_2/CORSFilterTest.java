package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testFilterExecutionOnV1All() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFilterExecutionOnV1Alpha() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFilterExecutionOnV2All() {
        given()
            .when()
            .get("/v2/all")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFilterExecutionOnV2Alpha() {
        given()
            .when()
            .get("/v2/alpha/US")
            .then()
            .statusCode(200);
    }
}