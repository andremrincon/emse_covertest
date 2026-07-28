package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        int status = given()
                .pathParam("region", "123")
                .when()
                .get("/v1/region/{region}")
                .then()
                .extract()
                .jsonPath()
                .getInt("status");

        assertEquals(404, status);
    }

    @Test(timeout = 60000)
    public void testGetMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        String message = given()
                .pathParam("capital", "123")
                .when()
                .get("/v1/capital/{capital}")
                .then()
                .extract()
                .jsonPath()
                .getString("message");

        assertEquals("Not Found", message);
    }
}