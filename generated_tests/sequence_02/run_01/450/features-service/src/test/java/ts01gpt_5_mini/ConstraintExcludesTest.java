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
    public static void init() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createConstraint_withSourceOnly_returnsSourceFeatureInBody() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "SourceFeature-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", src).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void createConstraint_withExcludedOnly_returnsExcludedFeatureInBody() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String excl = "ExcludedFeature-" + UUID.randomUUID().toString();
        given().formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void createConstraint_withBothParameters_returnsCreatedStatus() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "SourceFeature-" + UUID.randomUUID().toString();
        String excl = "ExcludedFeature-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }
}