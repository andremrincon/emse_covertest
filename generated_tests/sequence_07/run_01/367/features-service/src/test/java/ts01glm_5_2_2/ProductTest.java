package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductReturns201() {
        String productName = "TestProduct-AddFeature-" + java.util.UUID.randomUUID();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description for feature")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromProductReturns204() {
        String productName = "TestProduct-RemoveFeature-" + java.util.UUID.randomUUID();
        String featureName = "TestFeature-Remove-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature to be removed")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void buildWithFeaturesViaGetConfigurationFeaturesReturns200() {
        String productName = "TestProduct-BuildFeatures-" + java.util.UUID.randomUUID();
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID();
        String featureName = "TestFeature-Cfg-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature for configuration")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void buildWithFeaturesViaGetProductReturns200() {
        String productName = "TestProduct-BuildGet-" + java.util.UUID.randomUUID();
        String featureName = "TestFeature-Get-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature for product retrieval")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200)
            .body("name", equalTo(productName));
    }

    @Test(timeout = 60000)
    public void addFeatureConstraintRequiresReturns201() {
        String productName = "TestProduct-ReqConstraint-" + java.util.UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{sourceFeature}", productName, sourceFeature)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{requiredFeature}", productName, requiredFeature)
        .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureConstraintExcludesReturns201() {
        String productName = "TestProduct-ExclConstraint-" + java.util.UUID.randomUUID();
        String sourceFeature = "ExclSource-" + java.util.UUID.randomUUID();
        String excludedFeature = "ExclTarget-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{sourceFeature}", productName, sourceFeature)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{excludedFeature}", productName, excludedFeature)
        .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureWithDescriptionReturns201() {
        String productName = "TestProduct-AddFeatDesc-" + java.util.UUID.randomUUID();
        String featureName = "TestFeature-Desc-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "A detailed description of the feature being added")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void removeFeatureThenGetProductFeaturesReturns200() {
        String productName = "TestProduct-RemoveGet-" + java.util.UUID.randomUUID();
        String featureName = "TestFeature-RemoveGet-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));
        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/features", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void buildWithFeaturesViaGetProductFeaturesReturns200() {
        String productName = "TestProduct-BuildFeatsList-" + java.util.UUID.randomUUID();
        String featureName1 = "TestFeature-F1-" + java.util.UUID.randomUUID();
        String featureName2 = "TestFeature-F2-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName1}", productName, featureName1)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName2}", productName, featureName2)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/features", productName)
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(2));
    }

    @Test(timeout = 60000)
    public void addFeatureConstraintRequiresWithExistingFeaturesReturns201() {
        String productName = "TestProduct-ReqExisting-" + java.util.UUID.randomUUID();
        String sourceFeature = "RAID-Controller-Card-" + java.util.UUID.randomUUID();
        String requiredFeature = "128GB-ECC-RAM-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{sourceFeature}", productName, sourceFeature)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{requiredFeature}", productName, requiredFeature)
        .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureConstraintExcludesWithExistingFeaturesReturns201() {
        String productName = "TestProduct-ExclExisting-" + java.util.UUID.randomUUID();
        String sourceFeature = "CPU-i9-" + java.util.UUID.randomUUID();
        String excludedFeature = "Integrated-Graphics-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{sourceFeature}", productName, sourceFeature)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{excludedFeature}", productName, excludedFeature)
        .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void buildWithFeaturesViaGetConfigurationReturns200() {
        String productName = "TestProduct-BuildCfg-" + java.util.UUID.randomUUID();
        String configurationName = "TestCfg-" + java.util.UUID.randomUUID();
        String featureName = "TestFeature-CfgBuild-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
        .then()
            .statusCode(200);
    }
}