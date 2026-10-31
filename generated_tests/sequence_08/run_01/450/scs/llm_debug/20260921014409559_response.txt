package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testTitleMaleMr() {
        RestAssured.baseURI = getBaseUrl();
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testTitleFemaleMrs() {
        RestAssured.baseURI = getBaseUrl();
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testTitleNoneDr() {
        RestAssured.baseURI = getBaseUrl();
        given()
            .pathParam("sex", "none")
            .pathParam("title", "dr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testTitleOther() {
        RestAssured.baseURI = getBaseUrl();
        given()
            .pathParam("sex", "other")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testTitleMaleMrs() {
        RestAssured.baseURI = getBaseUrl();
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testTitleFemaleMr() {
        RestAssured.baseURI = getBaseUrl();
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("-1"));
    }
}