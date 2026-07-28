package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetSourceFeatureNameIsPersistedWhenCreatingExcludesConstraint() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String excludedFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then().statusCode(lessThan(300));

        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);

        act.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSetExcludedFeatureNameIsPersistedWhenCreatingExcludesConstraint() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String excludedFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then().statusCode(lessThan(300));

        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);

        act.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreatingExcludesConstraintWithBothFeaturesReturnsCreatedStatus() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String excludedFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then().statusCode(lessThan(300));

        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);

        act.then().statusCode(201);
    }
}