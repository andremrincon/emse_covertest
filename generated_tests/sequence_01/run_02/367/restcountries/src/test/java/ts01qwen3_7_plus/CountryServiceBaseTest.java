package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Code() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/DEU");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithDuplicates() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=US;US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithNull() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France?fullText=true");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchNameMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/Ger?fullText=false");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonInitialization() {
        Response response = given().when().get("/v1/all");
        response.then().statusCode(200);
    }
}