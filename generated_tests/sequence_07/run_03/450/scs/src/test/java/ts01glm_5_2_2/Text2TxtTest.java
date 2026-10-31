package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectForBranch() {
        given()
                .when()
                .get("/api/text2txt/for/quick/brown")
                .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectFourBranch() {
        given()
                .when()
                .get("/api/text2txt/four/quick/brown")
                .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYouBranch() {
        given()
                .when()
                .get("/api/text2txt/see/you/later")
                .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWayBranch() {
        given()
                .when()
                .get("/api/text2txt/by/the/way")
                .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testSubjectAreBranch() {
        given()
                .when()
                .get("/api/text2txt/are/quick/brown")
                .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectDefaultEmptyBranch() {
        given()
                .when()
                .get("/api/text2txt/hello/world/test")
                .then()
                .statusCode(200)
                .body(equalTo(""));
    }
}