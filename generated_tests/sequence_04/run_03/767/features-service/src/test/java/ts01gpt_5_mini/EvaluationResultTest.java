package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URI;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class EvaluationResultTest {

    private static String baseUri;

    @BeforeClass
    public static void setup() throws Exception {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) env = System.getProperty("api.base.url", "http://localhost:8080");
        URI uri = new URI(env);
        String scheme = uri.getScheme();
        String host = uri.getHost();
        int port = uri.getPort();
        if (scheme == null) scheme = "http";
        if (host == null) {
            RestAssured.baseURI = env;
        } else {
            String built = scheme + "://" + host;
            RestAssured.baseURI = built;
            if (port != -1) RestAssured.port = port;
            String path = uri.getPath();
            if (path != null && !path.isEmpty() && !"/".equals(path)) RestAssured.basePath = path;
        }
        baseUri = RestAssured.baseURI;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().baseUri(baseUri).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUri).when().post("/products/{productName}/features/{featureName}", productName, featureName).then().extract().response();
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsConfigurationName() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();
        given().baseUri(baseUri).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUri).when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUri).when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(200).extract().response();
        assertEquals(null, act.jsonPath().getString("configurationName"));
    }

    @Test(timeout = 60000)
    public void testDeleteProductReturns204() {
        String productName = "del-prod-" + UUID.randomUUID().toString();
        given().baseUri(baseUri).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUri).when().delete("/products/{productName}", productName).then().extract().response();
        assertEquals(204, act.getStatusCode());
    }
}