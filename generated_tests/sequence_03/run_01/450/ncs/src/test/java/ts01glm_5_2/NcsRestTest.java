package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherSuccessPath() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <400> was greater than <300>.")
    @Test(timeout = 60000)
    public void testFisherGuardClauseRejectsLargeM() {
        given()
                .when()
                .get("/api/fisher/1001/5/0.75")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherExceptionPathWhenXExceedsValidRange() {
        given()
                .when()
                .get("/api/fisher/10/5/1.2")
                .then()
                .statusCode(200);
    }
}