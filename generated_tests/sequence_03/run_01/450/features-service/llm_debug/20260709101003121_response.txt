package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUri");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URI");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_success() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String description = "Test feature description";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);

        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_duplicate_throws() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "dup-feature-" + UUID.randomUUID().toString();
        String description = "Duplicate test description";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "second attempt")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);

        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_removes_feature_from_configurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "to be removed from config")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);

        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_no_configurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-no-conf-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "no config")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);

        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_success() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);

        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_success() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-excl-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);

        act.then().statusCode(201);
    }
}