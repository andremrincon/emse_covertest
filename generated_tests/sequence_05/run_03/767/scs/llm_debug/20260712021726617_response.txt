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
public class Text2TxtTest {

    private String word1;
    private String word2;
    private String word3;
    private String expected;

    public Text2TxtTest(String word1, String word2, String word3, String expected) {
        this.word1 = word1;
        this.word2 = word2;
        this.word3 = word3;
        this.expected = expected;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "two", "x", "y", "2" },
            { "for", "x", "y", "4" },
            { "four", "x", "y", "4" },
            { "you", "x", "y", "u" },
            { "and", "x", "y", "n" },
            { "are", "x", "y", "r" },
            { "see", "you", "y", "cu" },
            { "see", "x", "y", "" },
            { "by", "the", "way", "btw" },
            { "by", "x", "way", "" },
            { "by", "the", "x", "" },
            { "x", "y", "z", "" }
        });
    }

    @Test(timeout = 60000)
    public void testText2Txt() {
        given()
            .pathParam("word1", word1)
            .pathParam("word2", word2)
            .pathParam("word3", word3)
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo(expected));
    }
}