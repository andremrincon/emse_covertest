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
    public void testDoFilterWithGetRequest() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/products/{productName}", productName);

        response.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithPostRequest() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();

        Response response = given()
            .when()
            .post("/products/{productName}", productName);

        response.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithPutRequest() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));
        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName);

        response.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithDeleteRequest() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));
        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName);

        response.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {
        Response response = given()
            .when()
            .options("/products");

        response.then().header("Access-Control-Allow-Origin", "*");
    }
}