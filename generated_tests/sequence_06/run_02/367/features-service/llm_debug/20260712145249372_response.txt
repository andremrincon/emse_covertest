package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithFeatures() {
        String productName = "ProdWithFeatures" + System.currentTimeMillis();
        String featureName = "FeatWith" + System.currentTimeMillis();
        String configurationName = "ConfWith" + System.currentTimeMillis();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithoutFeatures() {
        String productName = "ProdNoFeatures" + System.currentTimeMillis();
        String configurationName = "ConfNo" + System.currentTimeMillis();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(200);
    }
}