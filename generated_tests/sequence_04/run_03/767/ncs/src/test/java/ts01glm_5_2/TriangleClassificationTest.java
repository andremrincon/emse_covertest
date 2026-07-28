package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static final String BASE_URL = System.getProperty("baseUrl",
            System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testClassifyWithNonPositiveFirstSide() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/0/4/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyWithNonPositiveSecondSide() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/3/0/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyWithNonPositiveThirdSide() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/3/4/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateralTriangle() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/3/3/3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyInvalidTriangleWithFirstSideAsMax() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/5/1/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneTriangle() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/3/4/5")
        .then()
            .statusCode(200);
    }
}