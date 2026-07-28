package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
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
    public void testAddExcludesConstraintSetsSourceAndExcluded_nonEmptyBoth() {
        String product = "prod-" + UUID.randomUUID();
        String sourceFeature = "Source-" + UUID.randomUUID();
        String excludedFeature = "Excluded-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excludedFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_withMissingSourceFeature_callsSetterWithNull() {
        String product = "prod-" + UUID.randomUUID();
        String excludedFeature = "ExcludedOnly-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excludedFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_withMissingExcludedFeature_callsSetterWithNull() {
        String product = "prod-" + UUID.randomUUID();
        String sourceFeature = "SourceOnly-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_withSpecialCharactersInvokesSetters() {
        String product = "prod-" + UUID.randomUUID();
        String sourceFeature = "S p e c ! @ # $" + UUID.randomUUID();
        String excludedFeature = "Excl/\\?%&* " + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String encodedSource;
        String encodedExcluded;
        try {
            encodedSource = URLEncoder.encode(sourceFeature, "UTF-8");
            encodedExcluded = URLEncoder.encode(excludedFeature, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        given().when().post("/products/{productName}/features/{featureName}", product, encodedSource).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, encodedExcluded).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }
}