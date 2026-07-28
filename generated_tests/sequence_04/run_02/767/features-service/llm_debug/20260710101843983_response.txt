package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_success_returns201() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_duplicateFeature_throwsDuplicatedObjectException() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_nonExistentConfiguration_returnsServerError() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "NonExistentConfig-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(500);
    }
}