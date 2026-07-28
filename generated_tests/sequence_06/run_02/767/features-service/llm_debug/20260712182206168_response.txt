package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintReturns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String source = "src-" + uuid;
        String required = "req-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConstraintAddsRequiredFeatureWhenSourceActive() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String source = "src-" + uuid;
        String required = "req-" + uuid;
        String config = "conf-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source)
                .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config)
                .then().body("$", hasItem(source));
    }

    @Test(timeout = 60000)
    public void testConstraintDoesNotAddWhenSourceInactive() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String source = "src-" + uuid;
        String required = "req-" + uuid;
        String config = "conf-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config)
                .then().body("$", not(hasItem(required)));
    }
}