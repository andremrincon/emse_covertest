package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        RestAssured.urlEncodingEnabled = false;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() throws Exception {
        String txt = URLEncoder.encode("http://a/b", "UTF-8");
        given().when().get("/api/pat/" + txt).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() throws Exception {
        given().when().get("/api/pat/mon01jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() throws Exception {
        String txt = URLEncoder.encode("12.34e+56", "UTF-8");
        given().when().get("/api/pat/" + txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() throws Exception {
        given().when().get("/api/pat/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatchFtp() throws Exception {
        String txt = URLEncoder.encode("ftp://x/y", "UTF-8");
        given().when().get("/api/pat/" + txt).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatchNegativeExponent() throws Exception {
        String txt = URLEncoder.encode("1.0e-99", "UTF-8");
        given().when().get("/api/pat/" + txt).then().statusCode(200);
    }
}