package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testMaleValidTitle() {
        Response response = given()
                .when()
                .get(baseUrl + "/api/title/male/mr");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        Response response = given()
                .when()
                .get(baseUrl + "/api/title/male/invalid");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitle() {
        Response response = given()
                .when()
                .get(baseUrl + "/api/title/female/mrs");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        Response response = given()
                .when()
                .get(baseUrl + "/api/title/female/invalid");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitle() {
        Response response = given()
                .when()
                .get(baseUrl + "/api/title/none/dr");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherSex() {
        Response response = given()
                .when()
                .get(baseUrl + "/api/title/other/invalid");

        response.then().statusCode(200);
    }
}