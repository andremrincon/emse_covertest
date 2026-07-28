package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String envUrl = System.getenv("BASE_URL");
        if (envUrl != null && !envUrl.isEmpty()) {
            RestAssured.baseURI = envUrl;
        } else {
            RestAssured.baseURI = System.getProperty("baseURL", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintAddsDerivedFeature() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String sourceFeature = "Source_" + UUID.randomUUID().toString();
        String requiredFeature = "Required_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintSourceNotActive() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String sourceFeature = "Source_" + UUID.randomUUID().toString();
        String requiredFeature = "Required_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().body("$", not(hasItem(requiredFeature)));
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintBothFeaturesActive() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String sourceFeature = "Source_" + UUID.randomUUID().toString();
        String requiredFeature = "Required_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testConstraintRequiresType() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String sourceFeature = "Source_" + UUID.randomUUID().toString();
        String requiredFeature = "Required_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName).then().body("constraints.type", hasItem("requires"));
    }
}