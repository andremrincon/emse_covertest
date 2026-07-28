package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherReturnsOneWhenPGreaterThanOne() {
        given()
            .when()
            .get("/api/fisher/4/4/-0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturnsZeroWhenPLessThanZero() {
        given()
            .when()
            .get("/api/fisher/2/2/-0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturnsPWhenPBetweenZeroAndOne() {
        given()
            .when()
            .get("/api/fisher/1/1/0.5")
            .then()
            .statusCode(200);
    }
}