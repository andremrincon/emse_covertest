package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForNonExistentPath() {
        given()
                .when()
                .get("/v1/nonexistentpath")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForInvalidV2Path() {
        given()
                .when()
                .get("/v2/invalidpath/invalidsubpath")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForRootLevelUnknownPath() {
        given()
                .when()
                .get("/unknownendpoint")
                .then()
                .statusCode(404);
    }
}