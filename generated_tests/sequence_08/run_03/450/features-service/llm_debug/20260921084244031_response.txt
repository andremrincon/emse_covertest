package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testDoFilterWithGetRequest() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }
}