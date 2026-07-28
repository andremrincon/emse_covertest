package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE_URL");
        String base = prop != null ? prop : (env != null ? env : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void optionsRequest_setsAllowMethodsHeader() {
        given()
        .when()
            .options("/")
        .then()
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void getFeatures_setsAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given()
        .when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300));
        given()
        .when()
            .get("/products/{productName}/features", productName)
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void addFeature_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given()
        .when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Auto-generated feature description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeature_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given()
        .when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Temporary feature for deletion")
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
    public void getConfigurationFeatures_setsAccessControlMaxAgeHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given()
        .when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300));
        given()
        .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
        .then()
            .statusCode(lessThan(300));
        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
        .then()
            .header("Access-Control-Max-Age", "3600");
    }
}