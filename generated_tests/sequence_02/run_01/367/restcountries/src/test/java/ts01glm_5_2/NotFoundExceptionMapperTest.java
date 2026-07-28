package ts01glm_5_2;

import io.restassured.RestAssured;
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
    public void toResponse_returnsNotFound_whenNonExistentPathRequested() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/v1/nonexistent-endpoint-" + java.util.UUID.randomUUID())
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_returnsNotFound_whenUnknownRootPathRequested() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/unknown-root-path-" + java.util.UUID.randomUUID())
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_returnsNotFound_whenDeepNonExistentPathRequested() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/v1/alpha/US/extra/segment/" + java.util.UUID.randomUUID())
                .then()
                .statusCode(404);
    }
}