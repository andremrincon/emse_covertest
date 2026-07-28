package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

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
    public void testSubject_i28_branchI0True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i7_branchI1True() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i3_allBranchesFalse() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i0_branchI2TrueI3False() {
        given()
            .when()
                .get("/api/notypevar/0/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_invalidI_returns400() {
        given()
            .when()
                .get("/api/notypevar/abc/test")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubject_i5_sExampleString() {
        given()
            .when()
                .get("/api/notypevar/5/example-string")
            .then()
                .statusCode(200);
    }
}