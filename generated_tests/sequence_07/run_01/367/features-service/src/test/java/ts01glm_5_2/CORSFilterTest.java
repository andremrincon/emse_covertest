package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseURI = System.getProperty("baseURI");
        if (baseURI != null && !baseURI.isEmpty()) {
            RestAssured.baseURI = baseURI;
        }
        String port = System.getProperty("port");
        if (port != null && !port.isEmpty()) {
            try {
                RestAssured.port = Integer.parseInt(port);
            } catch (NumberFormatException e) {
                RestAssured.port = RestAssured.port;
            }
        }
    }

    @Test(timeout = 60000)
    public void testDoFilterProcessesGetRequest_1() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterProcessesOptionsRequest_1() {
        given()
            .when()
                .options("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterProcessesPostRequest_1() {
        String productName = "CORS-Post-" + java.util.UUID.randomUUID().toString();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDoFilterProcessesDeleteRequest_1() {
        String productName = "CORS-Del-" + java.util.UUID.randomUUID().toString();
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
    public void testDoFilterProcessesPutRequest_1() {
        String productName = "CORS-Put-" + java.util.UUID.randomUUID().toString();
        String featureName = "feat-" + java.util.UUID.randomUUID().toString();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));
        given()
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));
        given()
            .when()
                .put("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsCorsHeadersOnNonOptionsRequest_1() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }
}