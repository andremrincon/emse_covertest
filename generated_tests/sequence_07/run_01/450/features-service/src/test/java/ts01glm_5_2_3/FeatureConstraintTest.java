package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintTriggersFeatureConstraintSetId() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Enterprise-Server-X1-" + uniqueSuffix;
        String sourceFeature = "RAID-Controller-Card-" + uniqueSuffix;
        String requiredFeature = "128GB-ECC-RAM-" + uniqueSuffix;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then().statusCode(lessThan(300));

        given().when()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteRequiresConstraintTriggersFeatureConstraintSetId() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "UltraPhoneX-" + uniqueSuffix;
        String sourceFeature = "SSD-Storage-" + uniqueSuffix;
        String requiredFeature = "Graphics-Card-" + uniqueSuffix;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then().statusCode(lessThan(300));

        String location = given().when()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300))
                .extract().header("Location");

        given().when().get("/products/{productName}", productName).then().statusCode(200);

        given().when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, 1)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintTriggersFeatureConstraintSetId() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Laptop-Pro-15-" + uniqueSuffix;
        String sourceFeature = "CPU-i9-13900H-" + uniqueSuffix;
        String excludedFeature = "Integrated-Graphics-Only-" + uniqueSuffix;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then().statusCode(lessThan(300));

        given().when()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}", productName).then().statusCode(200);
    }
}