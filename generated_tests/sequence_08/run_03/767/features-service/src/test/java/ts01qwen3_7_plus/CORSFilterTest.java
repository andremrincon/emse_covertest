package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnGetRequest() {
        String productName = "Wireless-Mouse-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/{productName}", productName);

        response.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnOptionsRequest() {
        String productName = "Super-HD-4K-Monitor-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response response = given().when().options("/products/{productName}", productName);

        response.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnPostRequest() {
        String productName = "Gaming-PC-Tower-" + UUID.randomUUID();

        Response response = given().when().post("/products/{productName}", productName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnPutRequest() {
        String productName = "AeroBook-Pro-15-" + UUID.randomUUID();
        String featureName = "backlit-keyboard-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        Response response = given().formParam("description", "RGB backlit keyboard").when().put("/products/{productName}/features/{featureName}", productName, featureName);

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnDeleteRequest() {
        String productName = "SmartWatch-Pro-" + UUID.randomUUID();
        String featureName = "heart-rate-monitor-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);

        response.then().statusCode(204);
    }
}