package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintCreatesResource() {
        String uuid = UUID.randomUUID().toString();
        String productName = "test-product-requires-" + uuid;
        String sourceFeature = "source-" + uuid;
        String requiredFeature = "required-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        Response response = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintCreatesResource() {
        String uuid = UUID.randomUUID().toString();
        String productName = "test-product-excludes-" + uuid;
        String sourceFeature = "src-" + uuid;
        String excludedFeature = "excl-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        Response response = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToNonExistentProductReturnsServerError() {
        String uuid = UUID.randomUUID().toString();
        String productName = "nonexistent-product-" + uuid;
        String sourceFeature = "sf-" + uuid;
        String requiredFeature = "rf-" + uuid;

        Response response = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);

        response.then().statusCode(500);
    }
}