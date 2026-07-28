package ts01qwen3_7_plus;

import io.restassured.RestAssured;
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
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3CodeMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalidLength() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListValid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US;CA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/Germany?fullText=true").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/Federal%20Republic%20of%20Germany?fullText=true").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNoMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/Atlantis?fullText=true").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given().when().get("/v1/all").then().statusCode(200);
    }
}