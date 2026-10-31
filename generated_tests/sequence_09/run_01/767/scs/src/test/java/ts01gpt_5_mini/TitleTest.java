package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testMaleRecognizedTitleReturnsOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/title/male/mr").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedTitleReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/ms").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneRecognizedTitleReturnsTwo() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/title/none/prof").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnrecognizedCombinationReturnsNegativeOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/title/male/mrs").then().body(equalTo("-1"));
    }
}