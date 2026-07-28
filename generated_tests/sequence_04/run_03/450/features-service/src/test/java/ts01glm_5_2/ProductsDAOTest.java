package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @BeforeClass
    public static void setUp() {
        String baseUri = System.getProperty("baseUri");
        if (baseUri != null && !baseUri.isEmpty()) {
            RestAssured.baseURI = baseUri;
        } else {
            RestAssured.baseURI = "http://localhost";
        }
        String port = System.getProperty("port");
        if (port != null && !port.isEmpty()) {
            RestAssured.port = Integer.parseInt(port);
        } else {
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProduct_whenProductHasSingleConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProduct_whenProductHasNoConstraints() {
        String productName = "prod-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProduct_whenProductHasMultipleConstraints() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String feature1 = "f1-" + UUID.randomUUID().toString();
        String feature2 = "f2-" + UUID.randomUUID().toString();
        String feature3 = "f3-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + feature1)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + feature2)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + feature3)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature3)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName)
            .then()
                .statusCode(204);
    }
}