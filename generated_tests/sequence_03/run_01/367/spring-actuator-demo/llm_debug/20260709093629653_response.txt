package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSayHelloWithCustomName() {
        given()
            .baseUri("http://localhost:8080")
            .queryParam("name", "John%20Smith")
        .when()
            .get("/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithZeroDelay() {
        given()
            .baseUri("http://localhost:8080")
            .queryParam("delay", 0)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingAPIWithPositiveDelay() {
        given()
            .baseUri("http://localhost:8080")
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }
}