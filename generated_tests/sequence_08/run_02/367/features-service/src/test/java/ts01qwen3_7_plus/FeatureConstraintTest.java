package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String product = "TestProductReq";
        String f1 = "FeatureReqA";
        String f2 = "FeatureReqB";

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
            .when()
            .post("/products/" + product + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String product = "TestProductExcl";
        String f1 = "FeatureExclA";
        String f2 = "FeatureExclB";

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", f1)
            .formParam("excludedFeature", f2)
            .when()
            .post("/products/" + product + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String product = "TestProductGet";
        String f1 = "FeatureGetA";
        String f2 = "FeatureGetB";

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
            .when()
            .post("/products/" + product + "/constraints/requires")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + product)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraint() {
        String product = "TestProductDel";
        String f1 = "FeatureDelA";
        String f2 = "FeatureDelB";

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/" + f2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
            .when()
            .post("/products/" + product + "/constraints/requires")
            .then()
            .statusCode(lessThan(300));

        int constraintId = given()
            .when()
            .get("/products/" + product)
            .then()
            .extract()
            .path("constraints[0].id");

        given()
            .when()
            .delete("/products/" + product + "/constraints/" + constraintId)
            .then()
            .statusCode(204);
    }
}