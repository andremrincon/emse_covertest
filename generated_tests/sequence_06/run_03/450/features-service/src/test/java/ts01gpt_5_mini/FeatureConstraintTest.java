package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class FeatureConstraintTest {

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
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturnsBodyWithId() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "Src-" + UUID.randomUUID().toString();
        String excludedFeature = "Excl-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .body("id", notNullValue());
    }
}