package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasSize;

public class ProductConfigurationTest {

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
    public void testGetConfigurationReturns200() {
        String pid = "prod-" + UUID.randomUUID().toString();
        String conf = "conf-" + UUID.randomUUID().toString();
        String f1 = "feat-" + UUID.randomUUID().toString();
        String f2 = "feat-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", pid, f1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", pid, f2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, conf).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", pid, conf).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testConfigurationHasNoActivedFeaturesInitially() {
        String pid = "prod-" + UUID.randomUUID().toString();
        String conf = "conf-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, conf).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", pid, conf).then().body("activedFeatures", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String pid = "prod-" + UUID.randomUUID().toString();
        String conf = "conf-" + UUID.randomUUID().toString();
        String feat = "feat-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", pid, feat).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, conf).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", pid, conf, feat).then().statusCode(201);
    }
}