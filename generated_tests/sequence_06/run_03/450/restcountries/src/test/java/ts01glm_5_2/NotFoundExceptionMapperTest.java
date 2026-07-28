package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
    public void toResponse_nonExistentPath_returnsNotFound() {
        given()
                .when()
                .get("/v1/nonexistentpath/" + java.util.UUID.randomUUID().toString())
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_nonExistentRootPath_returnsNotFound() {
        given()
                .when()
                .get("/nonexistent-" + java.util.UUID.randomUUID().toString())
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_nonExistentV2SubPath_returnsNotFound() {
        given()
                .when()
                .get("/v2/nonexistent/" + java.util.UUID.randomUUID().toString())
                .then()
                .statusCode(404);
    }
}