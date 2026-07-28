package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testCorsFilterTransitiveExecution() {
        String productName = "Product-" + System.currentTimeMillis();

        RestAssured.given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        io.restassured.response.Response response = RestAssured.given()
                .when()
                .get("/products/" + productName);

        response.then().statusCode(200);
    }
}