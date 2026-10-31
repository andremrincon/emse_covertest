package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .pathParam("alphacode", "XYZ")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }
}