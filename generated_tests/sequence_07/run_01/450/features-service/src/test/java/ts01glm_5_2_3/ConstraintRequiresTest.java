package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithBothFeatures() {
        String productName = "TestProduct-Both-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithOnlySourceFeature() {
        String productName = "TestProduct-SourceOnly-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Push-Notifications";

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithOnlyRequiredFeature() {
        String productName = "TestProduct-ReqOnly-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "User-Authentication";

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithNoFeatures() {
        String productName = "TestProduct-NoFeatures-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintOnNonExistentProduct() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", "FeatureA")
                .formParam("requiredFeature", "FeatureB")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithNonExistentFeatures() {
        String productName = "TestProduct-MissingFeatures-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", "SSD-Storage")
                .formParam("requiredFeature", "Graphics-Card")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));
    }
}