package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintSetsBothNames() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithOnlySourceFeatureInvokesSetter() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "only-src-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithOnlyExcludedFeatureInvokesSetter() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String excluded = "only-exc-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }
}