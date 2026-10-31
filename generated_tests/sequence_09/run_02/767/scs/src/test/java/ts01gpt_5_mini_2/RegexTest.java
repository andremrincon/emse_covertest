package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_matchesUrl_returns200() throws Exception {
        String uid = UUID.randomUUID().toString();
        Response r1 = given().when().get("/api/pat/" + uid);
        assertThat(r1.getStatusCode(), lessThan(300));
        String txt = "http://a/b";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response r2 = given().when().get("/api/pat/" + enc);
        assertThat(r2.getStatusCode(), equalTo(200));
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_matchesDate_returns200() throws Exception {
        String uid = UUID.randomUUID().toString();
        Response r1 = given().when().get("/api/pat/" + uid);
        assertThat(r1.getStatusCode(), lessThan(300));
        String txt = "wed12jan";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response r2 = given().when().get("/api/pat/" + enc);
        assertThat(r2.getStatusCode(), equalTo(200));
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_matchesFpe_returns200() throws Exception {
        String uid = UUID.randomUUID().toString();
        Response r1 = given().when().get("/api/pat/" + uid);
        assertThat(r1.getStatusCode(), lessThan(300));
        String txt = "15.5e-04";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response r2 = given().when().get("/api/pat/" + enc);
        assertThat(r2.getStatusCode(), equalTo(200));
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_noneMatch_returns200() throws Exception {
        String uid = UUID.randomUUID().toString();
        Response r1 = given().when().get("/api/pat/" + uid);
        assertThat(r1.getStatusCode(), lessThan(300));
        String txt = "xyz";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response r2 = given().when().get("/api/pat/" + enc);
        assertThat(r2.getStatusCode(), equalTo(200));
    }

    @Test(timeout = 60000)
    public void testPatWithPattern_findsPattern_returns200() throws Exception {
        String uid = UUID.randomUUID().toString();
        Response r1 = given().when().get("/api/pat/" + uid);
        assertThat(r1.getStatusCode(), lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        String encTxt = URLEncoder.encode(txt, "UTF-8");
        String encPat = URLEncoder.encode(pat, "UTF-8");
        Response r2 = given().when().get("/api/pat/" + encTxt + "/" + encPat);
        assertThat(r2.getStatusCode(), equalTo(200));
    }

    @Test(timeout = 60000)
    public void testPatWithPattern_specialCharPattern_mayReturn500() throws Exception {
        String uid = UUID.randomUUID().toString();
        Response r1 = given().when().get("/api/pat/" + uid);
        assertThat(r1.getStatusCode(), lessThan(300));
        String txt = "some-text-to-search";
        String pat = "Ã§";
        String encTxt = URLEncoder.encode(txt, "UTF-8");
        String encPat = URLEncoder.encode(pat, "UTF-8");
        Response r2 = given().when().get("/api/pat/" + encTxt + "/" + encPat);
        assertThat(r2.getStatusCode(), equalTo(200));
    }
}