package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeaderV1All() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Origin", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderV1Alpha() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Methods", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeaderV1Currency() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Headers", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderV1Name() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        response.then().header("Cache-Control", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeaderV2All() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/all").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Origin", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderV2Alpha() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/alpha/US").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Methods", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeaderV2Currency() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/currency/EUR").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Headers", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderV2Name() {
        given().when().get("/v2/name/Germany").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/name/Germany").then().statusCode(200).extract().response();
        response.then().header("Cache-Control", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeaderV1Region() {
        given().when().get("/v1/region/Europe").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/region/Europe").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Origin", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderV1Capital() {
        given().when().get("/v1/capital/London").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/capital/London").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Methods", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeaderV1Callingcode() {
        given().when().get("/v1/callingcode/1").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/callingcode/1").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Headers", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderV2Region() {
        given().when().get("/v2/region/Europe").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/region/Europe").then().statusCode(200).extract().response();
        response.then().header("Cache-Control", nullValue(String.class));
    }
}