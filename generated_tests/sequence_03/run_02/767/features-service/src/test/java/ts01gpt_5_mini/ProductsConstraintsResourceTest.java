package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.trim().isEmpty()) {
            base = System.getProperty("api.base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201_statusCode() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString();
        String requiredFeature = "feat-req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));

        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product);

        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintLocationHeaderContainsConstraintSingular() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString();
        String excludedFeature = "feat-excl-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).when().post("/products/{productName}/features/{featureName}", product, excludedFeature).then().statusCode(lessThan(300));

        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", product);

        act.then().header("Location", containsString("/constraint/"));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintLocationHeaderContainsConstraintsPlural() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString();
        String requiredFeature = "feat-req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));

        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product);

        act.then().header("Location", containsString("/constraints/"));
    }
}