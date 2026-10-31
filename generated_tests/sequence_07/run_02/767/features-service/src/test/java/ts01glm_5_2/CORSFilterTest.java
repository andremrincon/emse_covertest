package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void doGetRequestPassesThroughCorsFilter() {
        RequestSpecification req = RestAssured.given();
        req.when()
            .get("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void doPostRequestPassesThroughCorsFilter() {
        String productName = "cors-post-" + java.util.UUID.randomUUID();
        RequestSpecification req = RestAssured.given();
        req.when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void doPutRequestPassesThroughCorsFilter() {
        String productName = "cors-put-" + java.util.UUID.randomUUID();
        String featureName = "test-feature";
        RequestSpecification req1 = RestAssured.given();
        req1.when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300));
        RequestSpecification req2 = RestAssured.given();
        req2.when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(lessThan(300));
        RequestSpecification req3 = RestAssured.given();
        req3.formParam("description", "test description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void doDeleteRequestPassesThroughCorsFilter() {
        String productName = "cors-delete-" + java.util.UUID.randomUUID();
        RequestSpecification req1 = RestAssured.given();
        req1.when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300));
        RequestSpecification req2 = RestAssured.given();
        req2.when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void doOptionsRequestSkipsChainDoFilter() {
        RequestSpecification req = RestAssured.given();
        req.when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void corsHeadersAreSetOnGetResponse() {
        RequestSpecification req = RestAssured.given();
        req.when()
            .get("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }
}