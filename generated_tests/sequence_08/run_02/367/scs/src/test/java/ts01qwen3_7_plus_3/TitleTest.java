package ts01qwen3_7_plus_3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test(timeout = 60000)
    public void testMaleMr() {
        given().when().get(BASE_URL + "/api/title/male/mr").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/api/title/male/mr");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleSmith() {
        given().when().get(BASE_URL + "/api/title/male/Smith").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/api/title/male/Smith");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMrs() {
        given().when().get(BASE_URL + "/api/title/female/mrs").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/api/title/female/mrs");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleSmith() {
        given().when().get(BASE_URL + "/api/title/female/Smith").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/api/title/female/Smith");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneDr() {
        given().when().get(BASE_URL + "/api/title/none/dr").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/api/title/none/dr");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneSmith() {
        given().when().get(BASE_URL + "/api/title/none/Smith").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/api/title/none/Smith");
        response.then().statusCode(200);
    }
}