package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_setsSourceAndRequired() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDerivedFeatureAddedWhenSourceActivated() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testNoDerivedFeatureWhenSourceNotActivated() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().body("$", not(hasItem(requiredFeature)));
    }
}