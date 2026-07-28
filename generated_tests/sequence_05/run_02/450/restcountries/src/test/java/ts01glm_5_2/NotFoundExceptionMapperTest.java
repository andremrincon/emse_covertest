package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenNonExistentPathRequested() {
        given().when().get("/v1/nonexistentpath").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenInvalidAlphaCodeTriggersNotFound() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenNonExistentSubPathRequested() {
        given().when().get("/v1/region/123").then().statusCode(404);
    }
}