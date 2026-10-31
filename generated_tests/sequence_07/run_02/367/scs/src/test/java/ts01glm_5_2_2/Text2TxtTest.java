package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectAreBranch() {
        given()
            .when()
                .get("/api/text2txt/are/you/ok")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYouBranch() {
        given()
            .when()
                .get("/api/text2txt/see/you/soon")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWayBranch() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectTwoBranch() {
        given()
            .when()
                .get("/api/text2txt/two/words/here")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectForBranch() {
        given()
            .when()
                .get("/api/text2txt/for/now/ok")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectAndBranch() {
        given()
            .when()
                .get("/api/text2txt/and/then/now")
            .then()
                .statusCode(200);
    }
}