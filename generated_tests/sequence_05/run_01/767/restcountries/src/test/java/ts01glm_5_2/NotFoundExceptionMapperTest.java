package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void toResponse_nonExistentPath_returns404() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/nonexistentpath")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_nonExistentV2Path_returns404() {
        given()
                .accept("application/json")
        .when()
                .get("/v2/nonexistentpath")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_rootNonExistentPath_returns404() {
        given()
                .accept("application/json")
        .when()
                .get("/nonexistent")
        .then()
                .statusCode(404);
    }
}