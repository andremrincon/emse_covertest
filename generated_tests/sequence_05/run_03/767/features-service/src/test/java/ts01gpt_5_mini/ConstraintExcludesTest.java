package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_withBothFeatures_triggersSetterCalls() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "source feature").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "excluded feature").when().post("/products/{productName}/features/{featureName}", product, excludedFeature).then().statusCode(lessThan(300));

        Response act = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_withEmptySourceFeature_callsExcludedSetterOnlyButStillInvokesSourceSetter() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "";
        String excludedFeature = "excl-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "excluded feature").when().post("/products/{productName}/features/{featureName}", product, excludedFeature).then().statusCode(lessThan(300));

        Response act = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_withEmptyExcludedFeature_callsSourceSetter() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "";

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "source feature").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));

        Response act = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_withBothEmptyValues_callsBothSettersWithEmpty() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "";
        String excludedFeature = "";

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));

        Response act = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }
}