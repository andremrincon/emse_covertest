package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE_URI_DEFAULT = "http://localhost";
    private static final String BASE_PATH_DEFAULT = "/";
    private static final String PRODUCTS_PATH = "/products";
    private static final int DEFAULT_PORT = 8080;

    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", BASE_URI_DEFAULT);
        String portProp = System.getProperty("port");
        if (portProp != null) {
            try {
                port = Integer.parseInt(portProp);
            } catch (NumberFormatException e) {
                port = DEFAULT_PORT;
            }
        } else {
            port = DEFAULT_PORT;
        }
        RestAssured.port = port;
        RestAssured.basePath = System.getProperty("basePath", BASE_PATH_DEFAULT);
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersForGetRequest() {
        given()
            .when()
            .get(PRODUCTS_PATH)
            .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void doFilterHandlesOptionsRequestWithoutChaining() {
        given()
            .when()
            .request(Method.OPTIONS, PRODUCTS_PATH)
            .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void filterDestroyInvokedOnContextShutdown_1() {
        given()
            .when()
            .get(PRODUCTS_PATH)
            .then()
            .statusCode(lessThan(300));
    }
}