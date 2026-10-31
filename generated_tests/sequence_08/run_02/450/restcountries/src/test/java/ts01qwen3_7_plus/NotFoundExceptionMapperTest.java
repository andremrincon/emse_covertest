package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @Test(timeout = 60000)
    public void testToResponseReturns404ForInvalidAlphaCode() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/all")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri("http://localhost:8080/rest")
            .pathParam("alphacode", "XYZ")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

}