package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withBothNames_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        String excl = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlySourceFeature_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlyExcludedFeature_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String excl = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }
}