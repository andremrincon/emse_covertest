package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevar_i0_true_i3_true() {
        given().when().get("/api/notypevar/28/a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/28/a").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i1_true() {
        given().when().get("/api/notypevar/7/a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/7/a").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i2_true_i3_false() {
        given().when().get("/api/notypevar/2/z").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/2/z").then().body(equalTo("2"));
    }
}