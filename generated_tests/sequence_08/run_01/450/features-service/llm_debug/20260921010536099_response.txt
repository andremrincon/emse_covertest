package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithNoConstraints() {
        String productName = "Prod1-" + UUID.randomUUID().toString();
        String configName = "Conf1-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithConstraintNoDerived() {
        String productName = "Prod2-" + UUID.randomUUID().toString();
        String configName = "Conf2-" + UUID.randomUUID().toString();
        String f1 = "F1-" + UUID.randomUUID().toString();
        String f2 = "F2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", f1).formParam("requiredFeature", f2).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + f2).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithConstraintAndDerived() {
        String productName = "Prod3-" + UUID.randomUUID().toString();
        String configName = "Conf3-" + UUID.randomUUID().toString();
        String f1 = "F3-" + UUID.randomUUID().toString();
        String f2 = "F4-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", f1).formParam("requiredFeature", f2).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + f1).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName + "/features");

        response.then().body("$", hasItem(f2));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraintInvalid() {
        String productName = "Prod4-" + UUID.randomUUID().toString();
        String configName = "Conf4-" + UUID.randomUUID().toString();
        String f1 = "F5-" + UUID.randomUUID().toString();
        String f2 = "F6-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + f2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", f1).formParam("excludedFeature", f2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + f2).then().statusCode(500);

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);
    }
}