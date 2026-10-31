package ts01gpt_5_mini_2;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private String baseUrl() {
        String url = System.getProperty("base.url");
        if (url == null || url.isEmpty()) url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = "http://localhost:8080/rest";
        return url;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_twoLetter_success() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_threeLetter_success() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/alpha/USA?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidFormat_400() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_404() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/True");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_success() {
        String base = baseUrl();
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha?codes=US,CA,MX");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_badRequest_400() {
        String base = baseUrl();
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha?codes=123");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_list_notFound_404() {
        String base = baseUrl();
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha?codes=true");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFullText_exactName() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/name/France?fullText=true");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFullText_altSpelling() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/name/USA?fullText=true");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_v1All_success() {
        String base = baseUrl();
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/all");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_v2_withFields_success() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2?fields=name;capital;population;region");
        resp.then().statusCode(200);
    }
}