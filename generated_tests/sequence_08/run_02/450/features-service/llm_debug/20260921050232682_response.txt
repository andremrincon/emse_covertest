package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Product-Create-Feature-" + System.currentTimeMillis();
        String featureName = "Feature-1";

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String productName = "Product-Update-Feature-" + System.currentTimeMillis();
        String featureName = "Feature-Update";
        String newDescription = "Updated description";

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", newDescription)
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeatures() {
        String productName = "Product-Get-Features-" + System.currentTimeMillis();
        String featureName = "Feature-Get";

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/features", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = "Product-Delete-Feature-" + System.currentTimeMillis();
        String featureName = "Feature-Delete";

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureSameNameDifferentProduct() {
        String productName1 = "Product-Diff-Prod-1-" + System.currentTimeMillis();
        String productName2 = "Product-Diff-Prod-2-" + System.currentTimeMillis();
        String featureName = "Shared-Feature";

        given().when().post("/products/{productName}", productName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productName2).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName1, featureName)
            .then()
            .statusCode(201);

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName2, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateMultipleFeaturesSameProduct() {
        String productName = "Product-Multi-Feat-" + System.currentTimeMillis();
        String featureName1 = "Feature-A";
        String featureName2 = "Feature-B";

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName1)
            .then()
            .statusCode(201);

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName2)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Product-Config-Feat-" + System.currentTimeMillis();
        String configName = "Config-1";
        String featureName = "Feature-Config";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(201);
    }
}