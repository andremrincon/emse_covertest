package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testToResponseWithInvalidAlphaCode() {
        given().when().get("/v1/all").then().statusCode(404);

        given()
            .pathParam("alphacode", "XYZ")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }
}