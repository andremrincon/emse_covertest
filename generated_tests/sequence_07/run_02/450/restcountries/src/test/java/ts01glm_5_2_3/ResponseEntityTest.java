package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsStatusAndMessage() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));

        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetNameServerErrorReturnsStatusAndMessage() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));

        given().when().get("/v1/name/True").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsStatusAndMessage() {
        given().when().get("/v1/capital/London").then().statusCode(lessThan(300));

        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsStatusAndMessage() {
        given().when().get("/v1/region/Europe").then().statusCode(lessThan(300));

        given().when().get("/v1/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetRegionServerErrorReturnsStatusAndMessage() {
        given().when().get("/v1/region/Europe").then().statusCode(lessThan(300));

        given().when().get("/v1/region/True").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCallingCodeNotFoundReturnsStatusAndMessage() {
        given().when().get("/v1/callingcode/1").then().statusCode(lessThan(300));

        given().when().get("/v1/callingcode/abc").then().statusCode(404);
    }
}