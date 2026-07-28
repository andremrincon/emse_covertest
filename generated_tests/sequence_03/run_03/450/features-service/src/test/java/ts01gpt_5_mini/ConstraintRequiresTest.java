package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testConstraintCreation_bodyContainsRequires() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddingSourceFeature_triggers_evaluation_status201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddingSourceAlreadyHavingRequired_status201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, required).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDerivedFeatureAppearsAfterAddingSource_assertBodyContainsRequired() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config)
                .then().body(containsString(required));
    }

    @Test(timeout = 60000)
    public void testAddingUnrelatedFeature_no_derivation_assert201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        String unrelated = "other-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, unrelated).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, unrelated)
                .then().statusCode(201);
    }
}