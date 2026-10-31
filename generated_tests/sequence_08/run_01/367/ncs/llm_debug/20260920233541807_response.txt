package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisherOddOdd() {
        given().when().get("/api/fisher/4/2/2.0").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/3/3/2.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEven() {
        given().when().get("/api/fisher/3/4/5.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOdd() {
        given().when().get("/api/fisher/4/3/2.0").then().statusCode(200);
    }
}