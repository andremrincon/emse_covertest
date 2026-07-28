package ts01qwen3_7_plus;

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
    public void testSubjectMaleProf() {
        given()
            .when()
                .get("/api/title/male/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFemaleProf() {
        given()
            .when()
                .get("/api/title/female/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneProf() {
        given()
            .when()
                .get("/api/title/none/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectOtherSex() {
        given()
            .when()
                .get("/api/title/other/prof")
            .then()
                .statusCode(200);
    }
}