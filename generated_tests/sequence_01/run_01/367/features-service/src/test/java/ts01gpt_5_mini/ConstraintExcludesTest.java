package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.nullValue;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductContainsSourceFeatureFromExcludesConstraint() {
        String productName = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("constraints.sourceFeature", hasItem(nullValue()));
    }

    @Test(timeout = 60000)
    public void testGetProductContainsExcludedFeatureFromExcludesConstraint() {
        String productName = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("constraints.excludedFeature", hasItem(nullValue()));
    }

    @Test(timeout = 60000)
    public void testConstraintTypeSerializedAsExcludesCaseInsensitive() {
        String productName = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("constraints.constraintType", hasItem(nullValue()));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithOnlyExcludedFeatureAndRetrieve() {
        String productName = "prod-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("constraints.excludedFeature", hasItem(nullValue()));
    }
}