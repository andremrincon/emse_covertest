package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/featureA").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescriptionToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description for feature")
            .when().post("/products/" + productName + "/features/featureDesc").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/featureRem").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/features/featureRem").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/srcFeatReq").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/reqFeatReq").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "srcFeatReq")
            .formParam("requiredFeature", "reqFeatReq")
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/srcFeatExc").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/excFeatExc").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "srcFeatExc")
            .formParam("excludedFeature", "excFeatExc")
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/confFeatA").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/confFeatA").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/confFeatB").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/confFeatB").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/confFeatB").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/featOne").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/featTwo").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesList() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/featList1").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/featList2").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/updFeat").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("description", "Updated feature description")
            .when().put("/products/" + productName + "/features/updFeat").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/cfgFeatGet").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/cfgFeatGet").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/delFeat1").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/delFeat2").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName).then().statusCode(204);
    }
}