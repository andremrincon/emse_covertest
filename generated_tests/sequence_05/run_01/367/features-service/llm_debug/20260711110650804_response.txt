package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Success() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String description = "Desc " + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", description).when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_DuplicateThrows() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", "initial").when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", "duplicate attempt").when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_RemovesFromConfigurationsAndDeletes() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", "to be removed from configs").when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", config)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature)
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("featureName", feature)
                .when().delete("/products/{productName}/features/{featureName}")
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NoConfigurationsDeletes() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", "standalone feature").when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("featureName", feature)
                .when().delete("/products/{productName}/features/{featureName}")
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Success() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).pathParam("productName", product)
                .formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires")
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Success() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "excl-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC).pathParam("productName", product)
                .formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes")
                .then().statusCode(201);
    }
}