package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAllowMethodsHeader() {
        given().when().get("/products").then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products")
            .then()
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAllowOriginHeader() {
        given().when().get("/products").then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products")
            .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterOptionsMethodSetsMaxAgeHeader() {
        given().when().options("/products").then().statusCode(lessThan(300));

        given()
            .when()
            .options("/products")
            .then()
            .header("Access-Control-Max-Age", "3600");
    }
}