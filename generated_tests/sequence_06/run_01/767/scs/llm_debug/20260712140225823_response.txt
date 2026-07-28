package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleValidTitle() {
        given()
            .when()
            .get("/api/title/male/mr")
            .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given()
            .when()
            .get("/api/title/male/mrs")
            .then()
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitle() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given()
            .when()
            .get("/api/title/female/mr")
            .then()
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneValidTitle() {
        given()
            .when()
            .get("/api/title/none/dr")
            .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherSex() {
        given()
            .when()
            .get("/api/title/other/mr")
            .then()
            .body(equalTo("-1"));
    }
}