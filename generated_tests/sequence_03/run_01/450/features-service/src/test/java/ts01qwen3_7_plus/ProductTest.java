package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Smartwatch-Series-8-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Blood-Oxygen-Sensor-" + UUID.randomUUID().toString();

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "SmartWatch-Pro-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "heart-rate-monitor-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Enterprise-Server-X1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "RAID-Controller-Card-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "128GB-ECC-RAM-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "CPU-i9-13900H-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Integrated-Graphics-Only-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature2)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }
}