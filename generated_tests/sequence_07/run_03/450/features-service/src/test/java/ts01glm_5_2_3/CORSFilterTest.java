package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String host = System.getenv("HOST");
        if (host == null || host.isEmpty()) {
            host = System.getProperty("host", "localhost");
        }
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = System.getProperty("port", "8080");
        }
        RestAssured.baseURI = "http://" + host + ":" + port;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnGetRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnOptionsRequest() {
        given()
            .when()
                .options("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testFilterChainContinuesForNonOptionsRequest() {
        String productName = "AeroBook-Pro-15-" + UUID.randomUUID().toString().substring(0, 8);
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/features")
            .then()
                .statusCode(200);
    }
}