package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String req = "Req-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + source).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + req).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", source)
            .formParam("requiredFeature", req)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_SourceActive_RequiredInactive() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String req = "Req-" + UUID.randomUUID();
        String config = "Conf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + source).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + req).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", req).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config + "/features/" + source).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + config + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_SourceActive_RequiredActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String req = "Req-" + UUID.randomUUID();
        String config = "Conf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + source).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + req).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", req).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config + "/features/" + source).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config + "/features/" + req).then().statusCode(500);

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + config + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_SourceInactive() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String req = "Req-" + UUID.randomUUID();
        String config = "Conf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + source).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + req).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", req).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + config + "/features")
            .then()
                .statusCode(200);
    }
}