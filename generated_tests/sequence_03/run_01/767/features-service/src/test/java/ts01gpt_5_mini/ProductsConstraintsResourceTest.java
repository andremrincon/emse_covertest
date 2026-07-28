package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base.url",
                System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String uuid = UUID.randomUUID().toString();
        String productName = "product-requires-" + uuid;
        String sourceFeature = "src-" + uuid;
        String requiredFeature = "req-" + uuid;

        given().pathParam("productName", productName)
                .when().post("/products/{productName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("featureName", sourceFeature)
                .when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("featureName", requiredFeature)
                .when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", productName)
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires")
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String uuid = UUID.randomUUID().toString();
        String productName = "product-excludes-" + uuid;
        String sourceFeature = "src-ex-" + uuid;
        String excludedFeature = "excl-" + uuid;

        given().pathParam("productName", productName)
                .when().post("/products/{productName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("featureName", sourceFeature)
                .when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("featureName", excludedFeature)
                .when().post("/products/{productName}/features/{featureName}")
                .then().statusCode(lessThan(300));

        given().pathParam("productName", productName)
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes")
                .then().statusCode(201);
    }
}