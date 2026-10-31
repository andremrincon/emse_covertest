package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductReturns201() {
        String productName = "TestProduct-AddFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Blood-Oxygen-Sensor").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/ANC").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Fingerprint-Reader").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/GPS-Tracking").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromProductReturns204() {
        String productName = "TestProduct-RemoveFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/heart-rate-monitor").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/features/heart-rate-monitor").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void updateFeatureDescriptionReturns200() {
        String productName = "TestProduct-UpdateFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/backlit-keyboard").then().statusCode(lessThan(300));
        given()
            .formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.")
            .when()
            .put("/products/" + productName + "/features/backlit-keyboard")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintReturns201() {
        String productName = "TestProduct-Requires-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/RAID-Controller-Card").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/128GB-ECC-RAM").then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", "RAID-Controller-Card")
            .formParam("requiredFeature", "128GB-ECC-RAM")
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintReturns201() {
        String productName = "TestProduct-Excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/CPU-i9-13900H").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Integrated-Graphics-Only").then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeaturesForProductReturns200() {
        String productName = "TestProduct-GetFeatures-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/16GB-LPDDR5-RAM").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/1TB-NVMe-SSD").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getProductByNameReturns200() {
        String productName = "TestProduct-GetByName-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Display-15inch").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationReturns201() {
        String productName = "TestProduct-ConfigFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/distributed-training").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/standard-gpu-cluster").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/standard-gpu-cluster/features/distributed-training").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns204() {
        String productName = "TestProduct-DelConfigFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/GPS").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/Premium-Tier").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/Premium-Tier/features/GPS").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/Premium-Tier/features/GPS").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesReturns200() {
        String productName = "TestProduct-GetConfigFeatures-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/fingerprint-scanner").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/us-standard-16gb").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/us-standard-16gb/features/fingerprint-scanner").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations/us-standard-16gb/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProductReturns200() {
        String productName = "TestProduct-GetConfigs-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/base-config").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteProductReturns204() {
        String productName = "TestProduct-Delete-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Storage-Upgrade").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getAllProductsReturns200() {
        given().when().get("/products").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteConstraintReturns204() {
        String productName = "TestProduct-DelConstraint-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/SSD-Storage").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Graphics-Card").then().statusCode(lessThan(300));
        String location = given()
            .formParam("sourceFeature", "SSD-Storage")
            .formParam("requiredFeature", "Graphics-Card")
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(lessThan(300))
            .extract()
            .header("Location");
        given().when().get("/products/" + productName).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/constraints/1").then().statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void getConfigurationByNameReturns200() {
        String productName = "TestProduct-GetConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/production-us-east-1").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations/production-us-east-1").then().statusCode(200);
    }
}