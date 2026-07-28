package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DuplicatedObjectExceptionTest {

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
    public void createDuplicateProductThrowsDuplicatedObjectException() {
        String productName = "DupProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createDuplicateFeatureThrowsDuplicatedObjectException() {
        String productName = "DupProd-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DupFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void createDuplicateConfigurationThrowsDuplicatedObjectException() {
        String productName = "DupConfProd-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "DupConf-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(201);
    }
}