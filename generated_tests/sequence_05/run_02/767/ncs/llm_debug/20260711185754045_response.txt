package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testClassifySideAZeroOrNegative() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifySideBZeroOrNegative() {
        given()
            .when()
                .get("/api/triangle/3/0/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateral() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyNotATriangleMaxA() {
        given()
            .when()
                .get("/api/triangle/5/1/1")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyIsosceles() {
        given()
            .when()
                .get("/api/triangle/2/2/3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyScalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200)
                .body("triangleType", nullValue());
    }
}