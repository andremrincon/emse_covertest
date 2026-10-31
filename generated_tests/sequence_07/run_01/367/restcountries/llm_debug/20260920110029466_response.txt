package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().statusCode(404).body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsResponseEntityWithStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/123").then().statusCode(404).body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsResponseEntityWithMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/123").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetLangNotFoundReturnsResponseEntityWithStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/123").then().statusCode(404).body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetSubregionNotFoundReturnsResponseEntityWithMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/subregion/123").then().body("message", equalTo("Not Found"));
    }
}