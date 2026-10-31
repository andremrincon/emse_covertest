package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequires() {
        String productName = "Prod-Req-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/FeatA").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/FeatB").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
            .formParam("sourceFeature", "FeatA")
            .formParam("requiredFeature", "FeatB")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludes() {
        String productName = "Prod-Exc-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/FeatA").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/FeatB").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
            .formParam("sourceFeature", "FeatA")
            .formParam("excludedFeature", "FeatB")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProduct() {
        String productName = "Prod-Del-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/FeatA").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/FeatB").then().statusCode(lessThan(300));
        given()
            .baseUri(baseUrl)
            .formParam("sourceFeature", "FeatA")
            .formParam("requiredFeature", "FeatB")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }
}