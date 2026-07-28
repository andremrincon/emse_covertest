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
    public void testNotFoundExceptionMapperReturns404ForUnknownPath() {
        given()
                .when()
                .get("/v1/nonexistentpath")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForUnknownNestedPath() {
        given()
                .when()
                .get("/v2/unknownresource/testvalue")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperReturns404ForInvalidRootPath() {
        given()
                .when()
                .get("/invalidroot")
                .then()
                .statusCode(404);
    }
}