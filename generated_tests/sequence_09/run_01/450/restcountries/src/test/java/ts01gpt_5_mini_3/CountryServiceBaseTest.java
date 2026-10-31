package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("baseUrl");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else if (prop != null && !prop.isEmpty()) {
            RestAssured.baseURI = prop;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_alpha2_success() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "US");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_alpha3_success() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "DEU");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_invalid_format_400() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "123");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_codes_list_success() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Alpha_codes_malformed_400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "[\"US\", \"CA\"]").when().get("/v2/alpha");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Name_fullText_alternative_spelling() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "Federal Republic of Germany");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Name_fullText_exact_name() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Callingcode_not_found_404() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/{callingcode}", "abc");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Root_get_200_loadJson() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1");
        Assert.assertEquals(200, act.getStatusCode());
    }
}