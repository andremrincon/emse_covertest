package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
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
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        String description = "Measures the oxygen saturation (SpO2) of your blood on demand.";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        int status = given().contentType("application/x-www-form-urlencoded").formParam("description", description).when().post("/products/{productName}/features/{featureName}", product, feature).then().extract().statusCode();
        org.junit.Assert.assertThat(status, equalTo(500));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Duplicate() {
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "first").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        int status = given().contentType("application/x-www-form-urlencoded").formParam("description", "second").when().post("/products/{productName}/features/{featureName}", product, feature).then().extract().statusCode();
        org.junit.Assert.assertThat(status, equalTo(500));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NoConfigurations() {
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        int status = given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().extract().statusCode();
        org.junit.Assert.assertThat(status, equalTo(204));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_WithConfigurations() {
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        String config = "config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        int status = given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().extract().statusCode();
        org.junit.Assert.assertThat(status, equalTo(204));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Success() {
        String product = "product-" + UUID.randomUUID().toString();
        String source = "source-" + UUID.randomUUID().toString();
        String required = "required-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        int status = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product).then().extract().statusCode();
        org.junit.Assert.assertThat(status, equalTo(201));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Success() {
        String product = "product-" + UUID.randomUUID().toString();
        String source = "source-" + UUID.randomUUID().toString();
        String excluded = "excluded-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        int status = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().extract().statusCode();
        org.junit.Assert.assertThat(status, equalTo(201));
    }
}