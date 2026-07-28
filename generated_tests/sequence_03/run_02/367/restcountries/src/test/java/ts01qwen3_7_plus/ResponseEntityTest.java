package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testResponseEntityNameNotFound() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityCapitalNotFound() {
        given()
            .when()
                .get("/v1/capital/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityRegionNotFound() {
        given()
            .when()
                .get("/v1/region/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityPostRootNotAllowed() {
        given()
            .when()
                .post("/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityPostV2NotAllowed() {
        given()
            .when()
                .post("/v2")
            .then()
                .statusCode(404);
    }
}