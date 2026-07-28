package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatchHttp() throws Exception {
        String txt = URLEncoder.encode("http://a/a", "UTF-8");
        given().urlEncodingEnabled(false).when().get("/api/pat/" + txt).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatchFtp() throws Exception {
        String txt = URLEncoder.encode("ftp://b/c", "UTF-8");
        given().urlEncodingEnabled(false).when().get("/api/pat/" + txt).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() throws Exception {
        when().get("/api/pat/mon01jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() throws Exception {
        String txt = URLEncoder.encode("12.34e+12", "UTF-8");
        given().urlEncodingEnabled(false).when().get("/api/pat/" + txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() throws Exception {
        when().get("/api/pat/hello").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatchGopher() throws Exception {
        String txt = URLEncoder.encode("gopher://x/y", "UTF-8");
        given().urlEncodingEnabled(false).when().get("/api/pat/" + txt).then().statusCode(400);
    }
}