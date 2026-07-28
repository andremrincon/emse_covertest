package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForNonExistentPath() {
        given()
                .when()
                .get("/v1/nonexistentpath/" + java.util.UUID.randomUUID().toString())
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForUnknownEndpoint() {
        given()
                .when()
                .get("/v2/unknownresource/" + java.util.UUID.randomUUID().toString())
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForInvalidSubPath() {
        given()
                .when()
                .get("/v1/alpha/" + java.util.UUID.randomUUID().toString() + "/invalid")
                .then()
                .statusCode(404);
    }
}