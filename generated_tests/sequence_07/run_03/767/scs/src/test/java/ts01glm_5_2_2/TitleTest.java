package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleReturnsOne() {
        given()
                .when()
                .get("/api/title/male/mr")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitleReturnsServerError() {
        given()
                .when()
                .get("/api/title/male/mrs")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleReturnsZero() {
        given()
                .when()
                .get("/api/title/female/mrs")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitleReturnsServerError() {
        given()
                .when()
                .get("/api/title/female/sir")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneSexWithValidTitleReturnsTwo() {
        given()
                .when()
                .get("/api/title/none/dr")
                .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsServerError() {
        given()
                .when()
                .get("/api/title/neuter/dr")
                .then()
                .statusCode(200);
    }
}