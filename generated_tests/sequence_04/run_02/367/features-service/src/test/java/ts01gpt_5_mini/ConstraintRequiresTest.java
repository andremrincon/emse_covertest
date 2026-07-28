package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyString;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "SRC-" + UUID.randomUUID().toString())
                .formParam("requiredFeature", "REQ-" + UUID.randomUUID().toString())
                .when()
                .post("/products/{productName}/constraints/requires", product)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConstraintCreationResponseContainsRequiredFeatureName() {
        String product = "prod-" + UUID.randomUUID().toString();
        String required = "REQ-" + UUID.randomUUID().toString();
        String source = "SRC-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", product)
                .then()
                .statusCode(201)
                .body(isEmptyString());
    }

    @Test(timeout = 60000)
    public void testProductGetContainsRequiresTypeAfterConstraintCreation() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "SRC-" + UUID.randomUUID().toString();
        String required = "REQ-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", product)
                .then()
                .statusCode(lessThan(300));
        given().when().get("/products/{productName}", product).then().statusCode(200).body(containsString("requires"));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationAddsRequiredWhenSourceAdded() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String source = "feature-src-" + UUID.randomUUID().toString();
        String required = "feature-req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config)
                .then().statusCode(200).body(containsString(required));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationDoesNotAddRequiredWhenSourceInactive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String source = "feature-src-" + UUID.randomUUID().toString();
        String required = "feature-req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config)
                .then().statusCode(200).body(not(containsString(required)));
    }
}