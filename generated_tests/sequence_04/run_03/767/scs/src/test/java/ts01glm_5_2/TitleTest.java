package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("port", "8080"));
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleMr() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitleMrs() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleMrs() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitleMr() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleDr() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "dr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUnknownSexWithMr() {
        given()
            .pathParam("sex", "other")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }
}