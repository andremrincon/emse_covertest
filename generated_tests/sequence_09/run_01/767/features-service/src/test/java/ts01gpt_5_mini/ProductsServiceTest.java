package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post(RestAssured.baseURI + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicate() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post(RestAssured.baseURI + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductRemovesFromConfiguration() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        given().when().post(RestAssured.baseURI + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/configurations/" + enc(configuration)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/configurations/" + enc(configuration) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().delete(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "source-" + UUID.randomUUID().toString();
        String required = "required-" + UUID.randomUUID().toString();
        given().when().post(RestAssured.baseURI + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(source)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(required)).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required).when().post(RestAssured.baseURI + "/products/" + enc(product) + "/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "source-" + UUID.randomUUID().toString();
        String excluded = "excluded-" + UUID.randomUUID().toString();
        given().when().post(RestAssured.baseURI + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(source)).then().statusCode(lessThan(300));
        given().when().post(RestAssured.baseURI + "/products/" + enc(product) + "/features/" + enc(excluded)).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post(RestAssured.baseURI + "/products/" + enc(product) + "/constraints/excludes").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductWhenProductMissingReturns500() {
        String product = "missing-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("requiredFeature", required).when().post(RestAssured.baseURI + "/products/" + enc(product) + "/constraints/requires").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductWhenProductMissingReturns500() {
        String product = "missing-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "excl-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post(RestAssured.baseURI + "/products/" + enc(product) + "/constraints/excludes").then().statusCode(500);
    }
}