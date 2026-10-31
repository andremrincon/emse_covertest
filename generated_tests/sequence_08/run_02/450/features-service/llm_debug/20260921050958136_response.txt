package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String product = "Product-Req-" + UUID.randomUUID().toString();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/ReqFeat").then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "SourceFeat")
            .formParam("requiredFeature", "ReqFeat")
        .when()
            .post("/products/" + product + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfig_SourceActive_RequiredInactive() {
        String product = "Product-Req-" + UUID.randomUUID().toString();
        String config = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/ReqFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "SourceFeat")
            .formParam("requiredFeature", "ReqFeat")
        .when()
            .post("/products/" + product + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config + "/features/SourceFeat").then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfig_BothActive() {
        String product = "Product-Req-" + UUID.randomUUID().toString();
        String config = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/ReqFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "SourceFeat")
            .formParam("requiredFeature", "ReqFeat")
        .when()
            .post("/products/" + product + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config + "/features/SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config + "/features/ReqFeat").then().statusCode(500);

        given().when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfig_SourceInactive() {
        String product = "Product-Req-" + UUID.randomUUID().toString();
        String config = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/features/ReqFeat").then().statusCode(lessThan(300));
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "SourceFeat")
            .formParam("requiredFeature", "ReqFeat")
        .when()
            .post("/products/" + product + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/" + product + "/configurations/" + config + "/features/ReqFeat").then().statusCode(lessThan(300));

        given().when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200);
    }
}