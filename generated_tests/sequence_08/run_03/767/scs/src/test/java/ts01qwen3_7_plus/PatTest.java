package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLe2() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/a/a").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/a/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoMatch() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/xyz/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/xyz/abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatPartialMatch() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/abx/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/abx/abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/abcx/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/abcx/abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevPartialMatch() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/abccbx/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/abccbx/abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundImmediately() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/abccba/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/abccba/abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundLater() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/abcxxcba/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/abcxxcba/abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevPartialMatch() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/cbx/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/cbx/abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundImmediately() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/api/pat/cbaabc/abc").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/pat/cbaabc/abc").then().statusCode(200);
    }
}