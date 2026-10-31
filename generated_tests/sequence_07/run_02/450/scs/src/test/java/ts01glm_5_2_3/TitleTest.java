package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class TitleTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMaleValidTitleMr() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/title/male/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitleMrs() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/title/female/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitleDr() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/title/none/dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/title/male/smith")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/title/female/smith")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInvalidTitle() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/title/none/smith")
            .then()
            .statusCode(200);
    }
}