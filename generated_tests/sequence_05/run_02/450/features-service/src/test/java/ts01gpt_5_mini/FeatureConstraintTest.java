package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.net.URI;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        try {
            URI uri = new URI(base);
            String scheme = uri.getScheme() != null ? uri.getScheme() : "http";
            String host = uri.getHost() != null ? uri.getHost() : uri.getPath();
            RestAssured.baseURI = scheme + "://" + host;
            if (uri.getPort() != -1) {
                RestAssured.port = uri.getPort();
            }
            if (uri.getPath() != null && !uri.getPath().isEmpty() && !uri.getPath().equals("/")) {
                RestAssured.basePath = uri.getPath();
            }
        } catch (Exception e) {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_returns201() {
        String productName = "prod-requires-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_returns201() {
        String productName = "prod-excludes-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String productName = "prod-feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(201);
    }
}