package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    private String uid() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaGetConfiguration() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductViaPostConfigurationFeature() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCollectFeatureNamesViaGetConfigurationFeatures() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActivedFeaturesContainsActivatedFeature() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200).body("", hasItem(feature));
    }

    @Test(timeout = 60000)
    public void testSetValidTrueOnConfigurationCreation() {
        String product = "p-" + uid();
        String config = "c-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetValidFalseViaExcludesConstraintViolation() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String f1 = "f-" + uid();
        String f2 = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f2).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", f1).formParam("excludedFeature", f2).when().post("/products/" + product + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + f2).then().statusCode(500);

        given().when().get("/products/" + product + "/configurations/" + config).then().body("valid", is(false));
    }

    @Test(timeout = 60000)
    public void testActiveFeatureViaPostConfigurationFeature() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetValidTrueAfterFixingExcludesConstraint() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String f1 = "f-" + uid();
        String f2 = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f2).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", f1).formParam("excludedFeature", f2).when().post("/products/" + product + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + f2).then().statusCode(500);

        given().when().delete("/products/" + product + "/configurations/" + config + "/features/" + f2).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeactiveFeatureViaDeleteConfigurationFeature() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(lessThan(300));

        given().when().delete("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeactiveNonActiveFeature() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given().when().delete("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureViaRequiresConstraint() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String source = "f-" + uid();
        String required = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + source).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + required).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/" + product + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + required).then().statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + source).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureViaExcludesConstraint() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String f1 = "f-" + uid();
        String f2 = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f2).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", f1).formParam("excludedFeature", f2).when().post("/products/" + product + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + f1).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCollectFeatureNamesEmptySet() {
        String product = "p-" + uid();
        String config = "c-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200).body("", empty());
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithNoProductFeatures() {
        String product = "p-" + uid();
        String config = "c-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActivedFeaturesAfterDeactivation() {
        String product = "p-" + uid();
        String config = "c-" + uid();
        String feature = "f-" + uid();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(lessThan(300));
        given().when().delete("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200).body("", not(hasItem(feature)));
    }
}