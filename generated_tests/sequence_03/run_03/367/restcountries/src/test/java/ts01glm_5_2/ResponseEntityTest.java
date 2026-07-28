package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntity() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/name/{name}", "123");

        response.then().statusCode(404);
        response.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/name/{name}", "123");

        response.then().statusCode(404);
        response.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsResponseEntityStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/capital/{capital}", "123");

        response.then().statusCode(404);
        response.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsResponseEntityMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/region/{region}", "123");

        response.then().statusCode(404);
        response.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetCallingCodeNotFoundReturnsResponseEntityStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/callingcode/{callingcode}", "abc");

        response.then().statusCode(404);
        response.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetAlphaNotFoundReturnsResponseEntityMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/alpha/{alphacode}", "XYZ");

        response.then().statusCode(404);
        response.then().body("message", equalTo("Not Found"));
    }
}