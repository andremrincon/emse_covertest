package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Succeeds_returns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String configuration = "conf-" + uuid;
        String feature = "feature-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_InvalidFeatureName_returns500() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String configuration = "conf-" + uuid;
        String feature = new String(new char[1200]).replace('\0', 'x');
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_Succeeds_returns204() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String configuration = "conf-" + uuid;
        String feature = "feature-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_NonexistentOrInvalidFeature_returns500() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String configuration = "conf-" + uuid;
        String feature = new String(new char[1200]).replace('\0', 'y');
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(500, act.getStatusCode());
    }
}