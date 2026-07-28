package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ProductsConfigurationFeaturesResourceTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_Success() {
        String productName = "Prod_Add_Success_" + System.currentTimeMillis();
        String configName = "Config_Add_Success_" + System.currentTimeMillis();
        String featureName = "Feature_Add_Success_" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(equalTo(201));
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_Failure() {
        String productName = "NonExistentProd_" + System.currentTimeMillis();
        String configName = "NonExistentConfig_" + System.currentTimeMillis();
        String featureName = "NonExistentFeature_" + System.currentTimeMillis();

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(equalTo(500));
    }

    @Test(timeout = 60000)
    public void deleteFeature_Success() {
        String productName = "Prod_Del_Success_" + System.currentTimeMillis();
        String configName = "Config_Del_Success_" + System.currentTimeMillis();
        String featureName = "Feature_Del_Success_" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(equalTo(204));
    }

    @Test(timeout = 60000)
    public void deleteFeature_Failure() {
        String productName = "NonExistentProd_" + System.currentTimeMillis();
        String configName = "NonExistentConfig_" + System.currentTimeMillis();
        String featureName = "NonExistentFeature_" + System.currentTimeMillis();

        given()
            .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(equalTo(500));
    }
}