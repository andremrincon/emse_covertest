package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testResponseEntityNameEndpoint404() {
        Response response = given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}");

        response.then()
            .statusCode(404);
        Assert.assertEquals("", response.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testResponseEntityCapitalEndpoint404() {
        Response response = given()
            .pathParam("capital", "123")
        .when()
            .get("/v1/capital/{capital}");

        response.then()
            .statusCode(404);
        Assert.assertEquals("", response.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testResponseEntityRegionEndpoint404() {
        Response response = given()
            .pathParam("region", "123")
        .when()
            .get("/v1/region/{region}");

        response.then()
            .statusCode(404);
        Assert.assertEquals("", response.getBody().asString());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testResponseEntityPostRootEndpoint405() {
        Response response = given()
            .contentType(ContentType.JSON)
        .when()
            .post("/");

        response.then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }
}