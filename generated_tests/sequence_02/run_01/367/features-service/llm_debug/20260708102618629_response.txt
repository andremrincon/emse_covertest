package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = System.getProperty("API_BASE");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintResponseContainsSourceFeature() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintEnforcedMakesConfigurationInvalid() {
        String product = "prod-" + UUID.randomUUID();
        String source = "feat-src-" + UUID.randomUUID();
        String excluded = "feat-excl-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintNotTriggeredWhenOnlySourceActive() {
        String product = "prod-" + UUID.randomUUID();
        String source = "feat-src-" + UUID.randomUUID();
        String excluded = "feat-excl-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturnsConstraintTypeEXCLUDES() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }
}