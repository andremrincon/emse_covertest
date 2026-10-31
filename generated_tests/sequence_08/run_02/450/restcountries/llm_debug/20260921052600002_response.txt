package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
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
    public void testAccessControlAllowOriginHeader() {
        Response response = given()
                .when()
                .get("/v1/all");

        response.then().statusCode(lessThan(300)).header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        Response response = given()
                .when()
                .get("/v1/all");

        response.then().statusCode(lessThan(300)).header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        Response response = given()
                .when()
                .get("/v1/all");

        response.then().statusCode(lessThan(300)).header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        Response response = given()
                .when()
                .get("/v1/all");

        response.then().statusCode(lessThan(300)).header("Cache-Control", equalTo(null));
    }
}