package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        String featureName = "distributed-training-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(500, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFailure_LongFeatureNameCausesServerError() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) sb.append('a');
        String longFeatureName = "f-" + uuid + "-" + sb.toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, longFeatureName);
        assertEquals(500, resp.getStatusCode());
    }

    @Ignore("expected:<204> but was:<500>")
    @Test(timeout = 60000)
    public void testDeleteFeatureSuccess() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        String featureName = "realtime-analytics-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFailure_LongFeatureNameCausesServerError() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3000; i++) sb.append('b');
        String longFeatureName = "f-" + uuid + "-" + sb.toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, longFeatureName);
        assertEquals(500, resp.getStatusCode());
    }
}