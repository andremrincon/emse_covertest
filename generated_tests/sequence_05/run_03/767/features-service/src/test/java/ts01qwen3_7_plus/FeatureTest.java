package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Prod-C-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feat-C-" + UUID.randomUUID().toString();
        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String productName = "Prod-U-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-U-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeatures() {
        String productName = "Prod-G-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-G-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfig() {
        String productName = "Prod-AC-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-AC-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        String configName = "Conf-AC-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigFeatures() {
        String productName = "Prod-GC-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-GC-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        String configName = "Conf-GC-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = "Prod-D-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-D-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }
}