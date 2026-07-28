package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleWithValidTitle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .when()
                .get("/api/title/male/mr");

        response.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .when()
                .get("/api/title/male/invalid");

        response.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .when()
                .get("/api/title/female/mrs");

        response.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .when()
                .get("/api/title/female/invalid");

        response.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .when()
                .get("/api/title/none/dr");

        response.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .when()
                .get("/api/title/none/invalid");

        response.then().body(equalTo("-1"));
    }
}