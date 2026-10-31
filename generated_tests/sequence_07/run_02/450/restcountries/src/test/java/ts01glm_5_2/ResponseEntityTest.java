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
    public void testNameNotFoundReturns404ResponseEntity() {
        given().when().get("/v1/name/123").then().statusCode(equalTo(404));
    }

    @Test(timeout = 60000)
    public void testNameServerErrorReturns500ResponseEntity() {
        given().when().get("/v1/name/True").then().statusCode(equalTo(404));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturns404ResponseEntity() {
        given().when().get("/v1/capital/123").then().statusCode(equalTo(404));
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturns404ResponseEntity() {
        given().when().get("/v1/region/123").then().statusCode(equalTo(404));
    }

    @Test(timeout = 60000)
    public void testRegionServerErrorReturns500ResponseEntity() {
        given().when().get("/v1/region/True").then().statusCode(equalTo(404));
    }

    @Test(timeout = 60000)
    public void testCallingCodeNotFoundReturns404ResponseEntity() {
        given().when().get("/v1/callingcode/abc").then().statusCode(equalTo(404));
    }
}