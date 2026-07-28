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
    public void testNameNotFoundReturnsStatusAndMessage() {
        given().when().get("/v1/name/123").then().statusCode(404).body("status", equalTo(404)).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturnsStatusAndMessage() {
        given().when().get("/v1/capital/123").then().statusCode(404).body("status", equalTo(404)).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturnsStatusAndMessage() {
        given().when().get("/v1/region/123").then().statusCode(404).body("status", equalTo(404)).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameServerErrorReturnsStatusAndMessage() {
        given().when().get("/v1/name/True").then().statusCode(404).body("status", equalTo(404)).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testCapitalServerErrorReturnsStatusAndMessage() {
        given().when().get("/v1/capital/NonExistentCapitalXYZ999").then().statusCode(404).body("status", equalTo(404)).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testRegionServerErrorReturnsStatusAndMessage() {
        given().when().get("/v1/region/True").then().statusCode(404).body("status", equalTo(404)).body("message", equalTo("Not Found"));
    }
}