package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
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
    public void testNotFoundExceptionMapperReturns404ForUnknownRootLevelPath() {
        given()
            .when()
                .get("/unknownendpoint/" + java.util.UUID.randomUUID().toString())
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForNonExistentV2Path() {
        given()
            .when()
                .get("/v2/nonexistent/" + java.util.UUID.randomUUID().toString())
            .then()
                .statusCode(404);
    }
}