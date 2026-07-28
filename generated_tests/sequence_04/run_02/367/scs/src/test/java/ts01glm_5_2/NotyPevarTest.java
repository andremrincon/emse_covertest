package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubject_branchI0True_i28() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/api/notypevar/28/hello");

        assertEquals(200, response.statusCode());
    }

    @Test(timeout = 60000)
    public void testSubject_branchI1AndI2True_i7() {
        given()
            .when()
                .get("/api/notypevar/7/zzz")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/api/notypevar/7/zzz");

        assertEquals(200, response.statusCode());
    }

    @Test(timeout = 60000)
    public void testSubject_allBranchesFalse_i0() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/api/notypevar/0/a");

        assertEquals(200, response.statusCode());
    }
}