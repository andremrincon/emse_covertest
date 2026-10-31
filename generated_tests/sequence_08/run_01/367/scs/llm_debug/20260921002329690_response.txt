package ts01qwen3_7_plus_2;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given().when().get(getBaseUrl() + "/api/cookie/userid/user1234/example.com").then().statusCode(lessThan(300));
        Response response = given().when().get(getBaseUrl() + "/api/cookie/userid/user1234/example.com");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidUserPrefix() {
        given().when().get(getBaseUrl() + "/api/cookie/userid/admin1234/example.com").then().statusCode(lessThan(300));
        Response response = given().when().get(getBaseUrl() + "/api/cookie/userid/admin1234/example.com");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        given().when().get(getBaseUrl() + "/api/cookie/userid/user1/example.com").then().statusCode(lessThan(300));
        Response response = given().when().get(getBaseUrl() + "/api/cookie/userid/user1/example.com");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given().when().get(getBaseUrl() + "/api/cookie/session/am/abc.com").then().statusCode(lessThan(300));
        Response response = given().when().get(getBaseUrl() + "/api/cookie/session/am/abc.com");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithPmAndAbcCom() {
        given().when().get(getBaseUrl() + "/api/cookie/session/pm/abc.com").then().statusCode(lessThan(300));
        Response response = given().when().get(getBaseUrl() + "/api/cookie/session/pm/abc.com");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given().when().get(getBaseUrl() + "/api/cookie/other/val/site").then().statusCode(lessThan(300));
        Response response = given().when().get(getBaseUrl() + "/api/cookie/other/val/site");
        response.then().statusCode(200);
    }
}