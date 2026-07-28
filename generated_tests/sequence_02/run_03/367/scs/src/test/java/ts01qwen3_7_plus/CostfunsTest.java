package ts01qwen3_7_plus;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_sEqualsBaab() {
        String url = BASE_URL + "/api/costfuns/5/baab";
        Response response = given().when().get(url);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanNeg444_sLessThanAbabba() {
        String url = BASE_URL + "/api/costfuns/-500/a";
        Response response = given().when().get(url);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iBetweenNeg444AndNeg333_sEqualsAbabba() {
        String url = BASE_URL + "/api/costfuns/-400/ababba";
        Response response = given().when().get(url);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_sGreaterThanAbabba() {
        String url = BASE_URL + "/api/costfuns/700/z";
        Response response = given().when().get(url);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iBetween555And666_sLessThanAbabba() {
        String url = BASE_URL + "/api/costfuns/600/aa";
        Response response = given().when().get(url);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsNeg4_sLessThanAbabba() {
        String url = BASE_URL + "/api/costfuns/-4/a";
        Response response = given().when().get(url);
        response.then().statusCode(200);
    }
}