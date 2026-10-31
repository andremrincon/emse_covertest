package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testGetFeatures_IncludesAccessControlAllowOriginHeader() {
        String product = "prod-get-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post(BASE + "/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", "desc").when().post(BASE + "/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().get(BASE + "/products/{productName}/features").then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testOptionsOnFeatures_ReturnsAllowMethodsHeader() {
        String product = "prod-opt-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post(BASE + "/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().options(BASE + "/products/{productName}/features").then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testAddFeature_PostSetsAccessControlMaxAgeHeader() {
        String product = "prod-post-" + UUID.randomUUID().toString();
        String feature = "feat-post-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post(BASE + "/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when()
                .post(BASE + "/products/{productName}/features/{featureName}").then().header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Returns204StatusCode() {
        String product = "prod-del-" + UUID.randomUUID().toString();
        String feature = "feat-del-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post(BASE + "/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).pathParam("featureName", feature)
                .formParam("description", "to be deleted").when().post(BASE + "/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().delete(BASE + "/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testPostConfigurationFeature_SetsAccessControlAllowHeaders() {
        String product = "prod-config-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-config-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post(BASE + "/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when()
                .post(BASE + "/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature)
                .when().post(BASE + "/products/{productName}/configurations/{configurationName}/features/{featureName}")
                .then().header("Access-Control-Allow-Headers", "x-requested-with");
    }
}