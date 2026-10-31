package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String basePath = System.getProperty("basePath", "/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = basePath;
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaAlphaNotFound() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaNameNotFound() {
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaCallingCodeNotFound() {
        given().when().get("/v1/callingcode/abc").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaCapitalNotFound() {
        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaRegionNotFound() {
        given().when().get("/v1/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaAlphaCodesNotFound() {
        given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha").then().statusCode(400);
    }
}