package ts01qwen3_7_plus_3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Test(timeout = 60000)
    public void testSubjectLine63() {
        Response response = given()
                .when()
                .get("http://localhost:8080/api/costfuns/0/b");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine24() {
        Response response = given()
                .when()
                .get("http://localhost:8080/api/costfuns/5/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine27() {
        Response response = given()
                .when()
                .get("http://localhost:8080/api/costfuns/-500/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine33() {
        Response response = given()
                .when()
                .get("http://localhost:8080/api/costfuns/700/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine42() {
        Response response = given()
                .when()
                .get("http://localhost:8080/api/costfuns/0/baba");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine39() {
        Response response = given()
                .when()
                .get("http://localhost:8080/api/costfuns/-4/abab");
        response.then().statusCode(200);
    }
}