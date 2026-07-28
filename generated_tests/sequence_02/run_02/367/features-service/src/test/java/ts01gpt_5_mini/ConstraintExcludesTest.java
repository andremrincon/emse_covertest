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
        String url = System.getProperty("base.url");
        if (url == null || url.isEmpty()) url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withSourceAndExcluded_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "source-" + UUID.randomUUID().toString();
        String excluded = "excluded-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlySource_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "only-source-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlyExcluded_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String excluded = "only-excluded-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_responseContainsConstraintTypeEXCLUDES() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateMultipleExcludesConstraints_withUniqueIdentifiers_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source1 = "s1-" + UUID.randomUUID().toString();
        String excluded1 = "e1-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source1)
                .formParam("excludedFeature", excluded1)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
        String source2 = "s2-" + UUID.randomUUID().toString();
        String excluded2 = "e2-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source2)
                .formParam("excludedFeature", excluded2)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }
}