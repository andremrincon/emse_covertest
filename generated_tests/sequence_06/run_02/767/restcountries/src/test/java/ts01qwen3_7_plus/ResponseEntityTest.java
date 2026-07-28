package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @Test(timeout = 60000)
    public void testGetStatus() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessage() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .body("message", equalTo("Not Found"));
    }
}