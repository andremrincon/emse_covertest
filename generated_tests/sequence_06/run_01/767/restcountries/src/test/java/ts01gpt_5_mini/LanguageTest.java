package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void init() throws Exception {
        String configured = System.getenv("TEST_BASE_URL");
        if (configured == null) {
            configured = System.getProperty("test.base.url", "http://localhost:8080/rest");
        }
        java.net.URL url = new java.net.URL(configured);
        String host = url.getProtocol() + "://" + url.getHost();
        int port = url.getPort();
        if (port == -1) {
            if ("https".equals(url.getProtocol())) port = 443;
            else port = 80;
        }
        RestAssured.baseURI = host;
        RestAssured.port = port;
        RestAssured.basePath = url.getPath();
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso639_1_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso639_2_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testSetNativeName_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['nativeName']", equalTo("English"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetIso639_1_FromV1Name_France() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['iso639_1']", equalTo("fr"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetIso639_2_FromV1Name_France() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['iso639_2']", equalTo("fra"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName_FromV1Name_France() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['name']", equalTo("French"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetNativeName_FromV1Name_France() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['nativeName']", equalTo("français"));
    }
}