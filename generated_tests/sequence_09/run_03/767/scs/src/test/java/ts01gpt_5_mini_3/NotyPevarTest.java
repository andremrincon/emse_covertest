package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returnsThree_whenSumIs28_and_otherConditions() {
        given().when().get("/api/notypevar/7/zzz").then().statusCode(lessThan(300));
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/28/aaa").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returnsZero_whenNoConditionsMatch() {
        given().when().get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/5/a").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returnsTwo_whenCompareToIsLess() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/1/zzzz").then().body(equalTo("2"));
    }
}