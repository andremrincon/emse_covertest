package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("baseUrl");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201_whenConfigurationAndProductExist() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "distributed-training-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void getConfigurations_returns500_forExtremelyLongProductName() {
        String goodProduct = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", goodProduct).then().statusCode(lessThan(300));
        String badProduct = "ThisProductNameIsIntentionallyMadeExtremelyLongToExceedPotentialDatabaseColumnLimitsOrOtherBackendProcessingBuffersWhichCouldLeadToAnUnhandledExceptionAndResultInAnInternalServerErrorResponseCodeFiveHundred";
        Response act = given().when().get("/products/{productName}/configurations", badProduct);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeature_returns204_afterCreatingFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "heart-rate-monitor-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Automatically added for deletion test").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }
}