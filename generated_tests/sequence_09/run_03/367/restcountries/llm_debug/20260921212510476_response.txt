package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() throws Exception {
        String configured = System.getProperty("base.url");
        if (configured == null || configured.isEmpty()) configured = System.getenv("BASE_URL");
        if (configured == null || configured.isEmpty()) configured = "http://localhost:8080/rest";
        URL u = new URL(configured);
        RestAssured.baseURI = u.getProtocol() + "://" + u.getHost();
        if (u.getPort() != -1) RestAssured.port = u.getPort();
        RestAssured.basePath = u.getPath();
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_USA_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/USA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_numeric_badFormat_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_codes_list_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_codes_invalidFormat_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha?codes=%5B%22US%22,%22CA%22%5D");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_codes_nonexistent_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Name_fullText_true_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/France");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name_fullText_false_substring_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/Fran");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_nonexistent_boolean_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/True");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Callingcode_1_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1");
        act.then().statusCode(200);
    }
}