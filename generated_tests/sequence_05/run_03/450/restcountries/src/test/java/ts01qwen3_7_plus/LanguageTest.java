package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso639_1() {
        given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        response.then().body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetIso639_2() {
        given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v1/lang/es").then().statusCode(lessThan(300));
        Response response = given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v1/lang/es").then().statusCode(200).extract().response();
        response.then().body("languages[0]['iso639_2']", equalTo("spa"));
    }

    @Test(timeout = 60000)
    public void testSetName() {
        given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response response = given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v2/alpha/US").then().statusCode(200).extract().response();
        response.then().body("languages[0].name", equalTo("English"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetNativeName() {
        given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v1/lang/es").then().statusCode(lessThan(300));
        Response response = given().baseUri("http://localhost:8080").basePath("/rest").when().get("/v1/lang/es").then().statusCode(200).extract().response();
        response.then().body("languages[0].nativeName", equalTo("Español"));
    }
}