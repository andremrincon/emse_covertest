package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Smartwatch-Series-8-AddFeature";
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", "Blood Oxygen Sensor")
            .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "SmartWatch-Pro-RemoveFeature";
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", "heart-rate-monitor")
        .when()
            .post("/products/{productName}/features/{featureName}")
            .then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", "heart-rate-monitor")
        .when()
            .delete("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Enterprise-Server-X1-Requires";
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", "RAID-Controller-Card")
        .when()
            .post("/products/{productName}/features/{featureName}")
            .then().statusCode(lessThan(300));
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", "128GB-ECC-RAM")
        .when()
            .post("/products/{productName}/features/{featureName}")
            .then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", "RAID-Controller-Card")
            .formParam("requiredFeature", "128GB-ECC-RAM")
        .when()
            .post("/products/{productName}/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Laptop-Pro-15-Excludes";
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", "CPU-i9-13900H")
        .when()
            .post("/products/{productName}/features/{featureName}")
            .then().statusCode(lessThan(300));
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", "Integrated-Graphics-Only")
        .when()
            .post("/products/{productName}/features/{featureName}")
            .then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(201);
    }
}