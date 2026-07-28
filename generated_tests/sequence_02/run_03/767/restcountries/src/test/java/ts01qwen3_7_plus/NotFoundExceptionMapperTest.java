package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapper() {
        given()
            .pathParam("alphacode", "XYZ")
        .when()
            .get("http://localhost:8080/rest/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }
}