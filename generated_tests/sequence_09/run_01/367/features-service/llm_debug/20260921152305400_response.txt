package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_adding_feature_with_requires_constraint_triggers_evaluation_and_returns_201() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        String product = "test-product-" + uid;
        String configuration = "cfg-" + uid;
        String featA = "feature-A-" + uid;
        String featB = "feature-B-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featA).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featB).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featA).formParam("requiredFeature", featB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featA);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_recursive_requires_constraints_processes_derived_features_and_returns_201() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        String product = "test-product-rec-" + uid;
        String configuration = "cfg-rec-" + uid;
        String featA = "featA-" + uid;
        String featB = "featB-" + uid;
        String featC = "featC-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featA).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featB).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featC).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featA).formParam("requiredFeature", featB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featB).formParam("requiredFeature", featC).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featA);
        act.then().statusCode(201);
    }
}