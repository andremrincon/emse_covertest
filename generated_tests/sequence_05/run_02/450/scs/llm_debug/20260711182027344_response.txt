package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class TitleTest {

    private String sex;
    private String title;
    private String expected;

    public TitleTest(String sex, String title, String expected) {
        this.sex = sex;
        this.title = title;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"male", "mr", "1"},
            {"male", "dr", "1"},
            {"male", "sir", "1"},
            {"male", "rev", "1"},
            {"male", "rthon", "1"},
            {"male", "invalid", "-1"},
            {"female", "mrs", "0"},
            {"female", "miss", "0"},
            {"female", "ms", "0"},
            {"female", "dr", "0"},
            {"female", "lady", "0"},
            {"female", "rev", "0"},
            {"female", "rthon", "0"},
            {"female", "prof", "0"},
            {"female", "invalid", "-1"},
            {"none", "dr", "2"},
            {"none", "rev", "2"},
            {"none", "rthon", "2"},
            {"none", "prof", "2"},
            {"none", "invalid", "-1"},
            {"other", "mr", "-1"}
        });
    }

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTitle() {
        given()
            .pathParam("sex", sex)
            .pathParam("title", title)
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo(expected));
    }
}