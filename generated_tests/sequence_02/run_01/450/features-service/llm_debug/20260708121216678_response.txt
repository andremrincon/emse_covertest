package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Product-" + System.nanoTime();
        String sourceFeature = "Source-" + System.nanoTime();
        String requiredFeature = "Required-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", requiredFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/{productName}/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredInactive() {
        String productName = "Product-" + System.nanoTime();
        String sourceFeature = "Source-" + System.nanoTime();
        String requiredFeature = "Required-" + System.nanoTime();
        String configName = "Config-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", requiredFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothActive() {
        String productName = "Product-" + System.nanoTime();
        String sourceFeature = "Source-" + System.nanoTime();
        String requiredFeature = "Required-" + System.nanoTime();
        String configName = "Config-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", requiredFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceInactive() {
        String productName = "Product-" + System.nanoTime();
        String sourceFeature = "Source-" + System.nanoTime();
        String requiredFeature = "Required-" + System.nanoTime();
        String configName = "Config-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", requiredFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .body("$", not(hasItem(requiredFeature)));
    }

    @Test(timeout = 60000)
    public void testConstraintTypeIsRequires() {
        String productName = "Product-" + System.nanoTime();
        String sourceFeature = "Source-" + System.nanoTime();
        String requiredFeature = "Required-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", requiredFeature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}")
            .then()
            .body("constraints.type", hasItem("requires"));
    }
}