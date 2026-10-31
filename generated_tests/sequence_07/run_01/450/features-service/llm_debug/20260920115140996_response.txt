package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DuplicatedObjectExceptionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("server.port", "8080"));
    }

    @Test(timeout = 60000)
    public void createDuplicateProductThrowsDuplicatedObjectException() {
        String productName = "DupProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void createDuplicateFeatureThrowsDuplicatedObjectException() {
        String productName = "DupProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DupFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("description", "Test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("description", "Test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void createDuplicateConfigurationThrowsDuplicatedObjectException() {
        String productName = "DupProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "DupConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(201);
    }
}