package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost";
            String port = System.getProperty("server.port");
            if (port != null && !port.isEmpty()) {
                RestAssured.port = Integer.parseInt(port);
            } else {
                RestAssured.port = 8080;
            }
        }
    }

    @Test(timeout = 60000)
    public void corsFilter_addsHeadersOnGetRequest() {
        String productName = "CORS-Test-Product-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .request(Method.OPTIONS, "/products/" + productName + "/features")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsFilter_handlesOptionsRequestWithoutForwarding() {
        String productName = "CORS-Options-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .request(Method.OPTIONS, "/products/" + productName + "/configurations")
            .then()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", containsString("OPTIONS"));
    }

    @Test(timeout = 60000)
    public void corsFilter_addsHeadersOnPostRequest() {
        String productName = "CORS-Post-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(anyOf(equalTo(200), equalTo(404), equalTo(500)))
                .header("Access-Control-Allow-Origin", "*");
    }
}