package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithNonOptionsRequest() {
        given()
                .when()
                .get("/products")
                .then()
                .header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {
        given()
                .when()
                .options("/products")
                .then()
                .header("Access-Control-Allow-Origin", equalTo("*"));
    }
}