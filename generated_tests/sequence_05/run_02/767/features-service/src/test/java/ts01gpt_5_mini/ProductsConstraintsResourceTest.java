package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        BASE = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String requiredFeature = "req-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        Response res = given().baseUri(BASE).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName);
        Assert.assertEquals(201, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintLocationHeaderContainsConstraintsPlural() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String requiredFeature = "req-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        Response res = given().baseUri(BASE).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName);
        String loc = res.getHeader("Location");
        Assert.assertTrue(loc != null && loc.contains("/products/" + productName + "/constraints/"));
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintLocationHeaderContainsConstraintSingular() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String excludedFeature = "excl-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        Response res = given().baseUri(BASE).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName);
        String loc = res.getHeader("Location");
        Assert.assertTrue(loc != null && loc.contains("/products/" + productName + "/constraint/"));
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintSecondCreationProducesDifferentLocation() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String excludedFeatureA = "exclA-" + UUID.randomUUID();
        String excludedFeatureB = "exclB-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, excludedFeatureA).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/features/{featureName}", productName, excludedFeatureB).then().statusCode(lessThan(300));
        Response first = given().baseUri(BASE).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeatureA).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300)).extract().response();
        Response second = given().baseUri(BASE).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeatureB).when().post("/products/{productName}/constraints/excludes", productName);
        String loc1 = first.getHeader("Location");
        String loc2 = second.getHeader("Location");
        Assert.assertTrue(loc1 != null && loc2 != null && !loc1.equals(loc2));
    }
}