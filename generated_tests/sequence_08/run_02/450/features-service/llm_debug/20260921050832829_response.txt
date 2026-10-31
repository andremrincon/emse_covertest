package ts01qwen3_7_plus_2;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post(BASE_URL + "/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post(BASE_URL + "/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete(BASE_URL + "/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post(BASE_URL + "/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();

        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post(BASE_URL + "/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }
}