package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv() != null ? System.getenv().getOrDefault("BASE_URL", "http://localhost:8080") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationLoadsConstraintEntity() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "prod-" + suffix;
        String sourceFeature = "src-feat-" + suffix;
        String requiredFeature = "req-feat-" + suffix;
        String configurationName = "cfg-" + suffix;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/configurations/" + configurationName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintEvaluationOnFeatureAdd() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "prod-" + suffix;
        String sourceFeature = "src-feat-" + suffix;
        String excludedFeature = "exc-feat-" + suffix;
        String configurationName = "cfg-" + suffix;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetProductLoadsConstraintEntity() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "prod-" + suffix;
        String sourceFeature = "src-feat-" + suffix;
        String requiredFeature = "req-feat-" + suffix;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName)
            .then().statusCode(200);
    }
}