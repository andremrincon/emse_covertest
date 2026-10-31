package ts01qwen3_7_plus_2;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class PatTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        Response response = given().when().get(BASE_URL + "/api/pat/ab/ab");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatNoMatch() {
        Response response = given().when().get(BASE_URL + "/api/pat/xyz/abc");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatMatchNoRev() {
        Response response = given().when().get(BASE_URL + "/api/pat/xabcx/abc");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRevMatchNoPat() {
        Response response = given().when().get(BASE_URL + "/api/pat/xcbax/abc");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndRevImmediate() {
        Response response = given().when().get(BASE_URL + "/api/pat/abccba/abc");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRevAndPatImmediate() {
        Response response = given().when().get(BASE_URL + "/api/pat/cbaabc/abc");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndRevDelayed() {
        Response response = given().when().get(BASE_URL + "/api/pat/abcxxcba/abc");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRevAndPatDelayed() {
        Response response = given().when().get(BASE_URL + "/api/pat/cbaxxabc/abc");
        response.then().statusCode(200);
    }
}