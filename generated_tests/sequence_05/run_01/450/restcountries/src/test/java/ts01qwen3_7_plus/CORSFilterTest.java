package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetV1All() {
        Response response = given().when().get("/v1/all");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Alpha() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Name() {
        Response response = given().when().get("/v1/name/France");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Currency() {
        Response response = given().when().get("/v1/currency/USD");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Callingcode() {
        Response response = given().when().get("/v1/callingcode/1");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Capital() {
        Response response = given().when().get("/v1/capital/London");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Region() {
        Response response = given().when().get("/v1/region/Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2All() {
        Response response = given().when().get("/v2/all");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2Alpha() {
        Response response = given().when().get("/v2/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2Name() {
        Response response = given().when().get("/v2/name/Germany");
        response.then().statusCode(200);
    }
}