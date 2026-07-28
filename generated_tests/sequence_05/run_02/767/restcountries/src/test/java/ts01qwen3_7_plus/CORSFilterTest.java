package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV1All() {
        Response response = given().when().get("/v1/all");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Alpha() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Name() {
        Response response = given().when().get("/v1/name/France");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Currency() {
        Response response = given().when().get("/v1/currency/USD");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Capital() {
        Response response = given().when().get("/v1/capital/London");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Region() {
        Response response = given().when().get("/v1/region/Europe");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2All() {
        Response response = given().when().get("/v2/all");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Alpha() {
        Response response = given().when().get("/v2/alpha/US");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Name() {
        Response response = given().when().get("/v2/name/Germany");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Currency() {
        Response response = given().when().get("/v2/currency/EUR");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Capital() {
        Response response = given().when().get("/v2/capital/Paris");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Region() {
        Response response = given().when().get("/v2/region/Europe");
        response.then().statusCode(404);
    }
}