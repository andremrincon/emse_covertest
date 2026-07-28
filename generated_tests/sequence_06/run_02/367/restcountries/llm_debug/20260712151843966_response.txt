package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeNoMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/XX");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3CodeMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/USA");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalidLength() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/123");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=US;CA");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListDuplicate() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=US;US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France?fullText=true");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all");
        response.then().statusCode(200);
    }
}