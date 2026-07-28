package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            if (baseUrl.startsWith("http://") || baseUrl.startsWith("https://")) {
                int schemeEnd = baseUrl.indexOf("://") + 3;
                String hostAndPort = baseUrl.substring(schemeEnd);
                String[] parts = hostAndPort.split("/", 2);
                String hostPort = parts[0];
                String basePath = parts.length > 1 ? "/" + parts[1] : "";
                String[] hp = hostPort.split(":");
                RestAssured.baseURI = "http://" + hp[0];
                RestAssured.port = hp.length > 1 ? Integer.parseInt(hp[1]) : 8080;
                RestAssured.basePath = basePath;
            } else {
                RestAssured.baseURI = "http://" + baseUrl;
            }
        } else {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAccessControlAllowOriginHeaderOnGetRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAccessControlAllowMethodsHeaderOnGetRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAccessControlAllowHeadersHeaderOnGetRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Headers", equalTo("x-requested-with"));
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAccessControlMaxAgeHeaderOnGetRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Max-Age", equalTo("3600"));
    }

    @Test(timeout = 60000)
    public void testDoFilterOptionsRequestSetsCorsHeadersWithoutChaining() {
        given()
            .when()
                .request(Method.OPTIONS, "/products")
            .then()
                .header("Access-Control-Allow-Origin", equalTo("*"))
                .header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void testDoFilterProcessesPostRequestThroughChain() {
        String productName = "CORSFilterTestProduct-" + java.util.UUID.randomUUID().toString();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo("*"));
    }
}