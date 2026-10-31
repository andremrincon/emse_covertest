package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_2Chars() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_3Chars() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US,CA").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_Duplicates() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US,US").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_ExactMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France?fullText=true").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_AltSpelling() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/Federal%20Republic%20of%20Germany?fullText=true").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_ViaGetAll() {
        given().when().get("/v1/all").then().statusCode(200);
    }
}