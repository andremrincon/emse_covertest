package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testSetLanguagePropertiesViaV1Alpha() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetLanguagePropertiesViaV1Lang() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/lang/es");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetLanguagePropertiesViaV2Alpha() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetLanguagePropertiesViaV2Lang() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/lang/Spanish");
        response.then().statusCode(404);
    }
}