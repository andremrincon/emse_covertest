package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        RestAssured.baseURI = base != null ? base : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesIncludesAdded() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d1").when().post("/products/{productName}/features/{featureName}", productName, f1).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d2").when().post("/products/{productName}/features/{featureName}", productName, f2).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().body("size()", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testBuildProductWithMultipleFeaturesReflectedInProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String a = "A-" + UUID.randomUUID().toString();
        String b = "B-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "A desc").when().post("/products/{productName}/features/{featureName}", productName, a).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "B desc").when().post("/products/{productName}/features/{featureName}", productName, b).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("features.size()", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "src desc").when().post("/products/{productName}/features/{featureName}", productName, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "req desc").when().post("/products/{productName}/features/{featureName}", productName, required).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String s = "s-" + UUID.randomUUID().toString();
        String excl = "e-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "s desc").when().post("/products/{productName}/features/{featureName}", productName, s).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "e desc").when().post("/products/{productName}/features/{featureName}", productName, excl).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", s).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }
}