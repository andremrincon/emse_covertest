package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMonJan() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTueFeb() {
        given().when().get("/api/dateparse/tue/feb").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWedMar() {
        given().when().get("/api/dateparse/wed/mar").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testThurApr() {
        given().when().get("/api/dateparse/thur/apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFriMay() {
        given().when().get("/api/dateparse/fri/may").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainingBranchesAndNoMatch() {
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/aug").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/dec").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/xyz/xyz").then().statusCode(200);
    }
}