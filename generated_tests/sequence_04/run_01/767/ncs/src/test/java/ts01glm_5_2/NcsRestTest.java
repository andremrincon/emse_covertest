package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherSuccessPath() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherBoundaryMEqual1000() {
        given()
                .when()
                .get("/api/fisher/1000/5/0.5")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherBoundaryNEqual1000() {
        given()
                .when()
                .get("/api/fisher/5/1000/0.5")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderSuccessPath() {
        given()
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(200)
                .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividend() {
        given()
                .when()
                .get("/api/remainder/-9/5")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderBoundaryLimit() {
        given()
                .when()
                .get("/api/remainder/10000/3")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}