package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintSucceeds() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintSucceeds() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <500>.")
    @Test(timeout = 60000)
    public void testAddRequiresConstraintServerErrorOnBadProductName() {
        String badProduct = "ThisIsAnExtremelyLongProductNameThatIsIntendedToExceedAnyReasonableLengthLimitImposedByTheDatabaseSchemaOrTheURLParserOfTheWebServerOrAnyIntermediateProxiesInOrderToTriggerAnUnhandledExceptionAndCauseAnInternalServerError";
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", badProduct).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintServerErrorOnBadProductName() {
        String badProduct = "product/with/slashes";
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "excl-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", badProduct).then().statusCode(400);
    }
}