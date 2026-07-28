package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusAndMessageOnNameNotFound() {
        String invalidName = "NonExistentCountryXYZ";

        Response response = given()
                .pathParam("name", invalidName)
                .when()
                .get("/v1/name/{name}");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusAndMessageOnRegionNotFound() {
        String invalidRegion = "NonExistentRegionXYZ";

        Response response = given()
                .pathParam("region", invalidRegion)
                .when()
                .get("/v1/region/{region}");

        response.then().statusCode(404);
    }
}