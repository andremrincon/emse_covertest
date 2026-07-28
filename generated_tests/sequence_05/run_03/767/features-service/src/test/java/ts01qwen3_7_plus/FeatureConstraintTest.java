package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Test(timeout = 60000)
    public void testSetIdViaRequiresConstraintCreation() {
        String productName = "ProdReq-" + System.nanoTime();
        String f1 = "F1-" + System.nanoTime();
        String f2 = "F2-" + System.nanoTime();

        given().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().post("/products/{productName}/features/{featureName}", productName, f1).then().statusCode(lessThan(300));
        given().post("/products/{productName}/features/{featureName}", productName, f2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetIdViaExcludesConstraintCreation() {
        String productName = "ProdExc-" + System.nanoTime();
        String f1 = "F1-" + System.nanoTime();
        String f2 = "F2-" + System.nanoTime();

        given().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().post("/products/{productName}/features/{featureName}", productName, f1).then().statusCode(lessThan(300));
        given().post("/products/{productName}/features/{featureName}", productName, f2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("excludedFeature", f2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }
}