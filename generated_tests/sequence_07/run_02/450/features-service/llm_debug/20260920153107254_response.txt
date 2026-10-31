package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE_URL_ENV = "BASE_URL";
    private static final String DEFAULT_BASE_URL = "http://localhost:8080";

    @BeforeClass
    public static void setUp() {
        String env = System.getenv(BASE_URL_ENV);
        String baseUrl = (env == null || env.isEmpty()) ? DEFAULT_BASE_URL : env;
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilterProcessesGetRequestAndChainsToNextFilter_1() {
        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnGetRequest_1() {
        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsAllowMethodsHeaderOnGetRequest_1() {
        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void doFilterSkipsChainForOptionsRequest_1() {
        RestAssured.given()
                .when()
                .options("/products")
                .then()
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnOptionsRequest_1() {
        RestAssured.given()
                .when()
                .options("/products")
                .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsMaxAgeHeaderOnPostRequest_1() {
        String productName = "CORS-Test-Product-" + java.util.UUID.randomUUID().toString();
        RestAssured.given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .header("Access-Control-Max-Age", "3600");
    }
}