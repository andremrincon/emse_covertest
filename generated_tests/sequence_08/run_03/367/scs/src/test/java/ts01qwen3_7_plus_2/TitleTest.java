package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitle() {
        Response response = given()
            .when()
            .get("/api/title/male/mr");

        response.then().statusCode(200).body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        Response response = given()
            .when()
            .get("/api/title/male/invalid");

        response.then().statusCode(200).body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitle() {
        Response response = given()
            .when()
            .get("/api/title/female/mrs");

        response.then().statusCode(200).body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        Response response = given()
            .when()
            .get("/api/title/female/invalid");

        response.then().statusCode(200).body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitle() {
        Response response = given()
            .when()
            .get("/api/title/none/dr");

        response.then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        Response response = given()
            .when()
            .get("/api/title/none/invalid");

        response.then().statusCode(200).body(equalTo("-1"));
    }
}