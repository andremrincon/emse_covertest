package ts01qwen3_7_plus_2;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String baseUrl = getBaseUrl();
        String productName = "Prod1_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));

        given().when().post(baseUrl + "/products/" + productName + "/features/Feat1").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String baseUrl = getBaseUrl();
        String productName = "Prod2_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat2").then().statusCode(lessThan(300));

        given().formParam("description", "Updated").when().put(baseUrl + "/products/" + productName + "/features/Feat2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeatures() {
        String baseUrl = getBaseUrl();
        String productName = "Prod3_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat3").then().statusCode(lessThan(300));

        given().when().get(baseUrl + "/products/" + productName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String baseUrl = getBaseUrl();
        String productName = "Prod4_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat4").then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/Conf4").then().statusCode(lessThan(300));

        given().when().post(baseUrl + "/products/" + productName + "/configurations/Conf4/features/Feat4").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureToConfiguration() {
        String baseUrl = getBaseUrl();
        String productName = "Prod5_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat5").then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/Conf5").then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/Conf5/features/Feat5").then().statusCode(lessThan(300));

        given().when().post(baseUrl + "/products/" + productName + "/configurations/Conf5/features/Feat5").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String baseUrl = getBaseUrl();
        String productName = "Prod6_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat6").then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/Conf6").then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/Conf6/features/Feat6").then().statusCode(lessThan(300));

        given().when().get(baseUrl + "/products/" + productName + "/configurations/Conf6/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String baseUrl = getBaseUrl();
        String productName = "Prod7_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat7").then().statusCode(lessThan(300));

        given().when().delete(baseUrl + "/products/" + productName + "/features/Feat7").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String baseUrl = getBaseUrl();
        String productName = "Prod8_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat8A").then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/Feat8B").then().statusCode(lessThan(300));

        given().formParam("sourceFeature", "Feat8A").formParam("requiredFeature", "Feat8B").when().post(baseUrl + "/products/" + productName + "/constraints/requires").then().statusCode(201);
    }
}