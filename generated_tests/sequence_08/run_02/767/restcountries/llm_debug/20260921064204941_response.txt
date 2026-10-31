package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2Letter() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3Letter() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNull() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListDuplicates() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,US").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/Deutschland").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().statusCode(200);
    }
}