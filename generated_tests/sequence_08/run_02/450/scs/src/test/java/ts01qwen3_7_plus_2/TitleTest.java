package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMaleProf() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "prof")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleUnknown() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "unknown")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleProf() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "prof")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleUnknown() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "unknown")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProf() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "prof")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneUnknown() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "unknown")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }
}