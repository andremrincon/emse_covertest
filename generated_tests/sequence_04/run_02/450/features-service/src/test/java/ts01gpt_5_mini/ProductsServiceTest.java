package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        String description = "Desc " + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response response = given().contentType(ContentType.URLENC).formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicateThrowsServerError() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        String description = "Desc " + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response response = given().contentType(ContentType.URLENC).formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(500, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWhenActiveInConfigurations() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        String configurationName = "conf-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "d")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        Response response = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWhenNotInConfigurations() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "d")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response response = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String requiredFeature = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response response = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-" + UUID.randomUUID();
        String excludedFeature = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response response = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        assertEquals(201, response.getStatusCode());
    }
}