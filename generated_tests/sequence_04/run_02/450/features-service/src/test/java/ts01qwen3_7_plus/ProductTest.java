package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testAddFeature() {
        String productName = "Product-AddFeature-" + UUID.randomUUID().toString();
        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-1";
        given()
            .when()
            .post(baseUrl + "/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature() {
        String productName = "Product-RemoveFeature-" + UUID.randomUUID().toString();
        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-2";
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete(baseUrl + "/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Product-Requires-" + UUID.randomUUID().toString();
        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));

        String feature1 = "Feature-A";
        String feature2 = "Feature-B";
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
            .post(baseUrl + "/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Product-Excludes-" + UUID.randomUUID().toString();
        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));

        String feature1 = "Feature-C";
        String feature2 = "Feature-D";
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when()
            .post(baseUrl + "/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }
}