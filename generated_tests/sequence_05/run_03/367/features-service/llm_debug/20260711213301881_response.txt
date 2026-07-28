package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));

        String featureName = "Feat-" + UUID.randomUUID().toString();

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when()
                .post("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));

        String featureName = "Feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when()
                .delete("/products/{productName}/features/{featureName}")
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));

        String feat1 = "Feat1-" + UUID.randomUUID().toString();
        String feat2 = "Feat2-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).pathParam("featureName", feat1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feat2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feat1)
                .formParam("requiredFeature", feat2)
                .when()
                .post("/products/{productName}/constraints/requires")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));

        String feat1 = "Feat1-" + UUID.randomUUID().toString();
        String feat2 = "Feat2-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).pathParam("featureName", feat1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feat2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feat1)
                .formParam("excludedFeature", feat2)
                .when()
                .post("/products/{productName}/constraints/excludes")
                .then()
                .statusCode(201);
    }
}