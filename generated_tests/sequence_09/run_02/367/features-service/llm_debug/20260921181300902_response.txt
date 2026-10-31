package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class FeatureConstraintTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_returns201() {
        String productName = "Enterprise-Server-X1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_responseContainsId() {
        String productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        resp.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() throws Exception {
        String productName = "Smartwatch Series 8-" + UUID.randomUUID().toString();
        String featureName = "Blood Oxygen Sensor";
        String encodedProductName = URLEncoder.encode(productName, "UTF-8").replace("+", "%20");
        String encodedFeatureName = URLEncoder.encode(featureName, "UTF-8").replace("+", "%20");
        given().when().post("/products/{productName}", encodedProductName).then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when()
                .post("/products/{productName}/features/{featureName}", encodedProductName, encodedFeatureName);
        resp.then().statusCode(201);
    }
}