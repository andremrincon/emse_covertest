package ts01gpt_5_mini_2;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

    private static final String BASE_URL = Optional.ofNullable(System.getProperty("base.url"))
            .orElseGet(() -> Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080"));

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() {
        given().when().get(BASE_URL + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/filesuffix/text/file.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() {
        given().when().get(BASE_URL + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/filesuffix/acrobat/document.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() {
        given().when().get(BASE_URL + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/filesuffix/word/report.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() {
        given().when().get(BASE_URL + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/filesuffix/bin/program.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() {
        given().when().get(BASE_URL + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/filesuffix/lib/library.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        given().when().get(BASE_URL + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/filesuffix/text/nosuffixfile");
        act.then().body(equalTo("0"));
    }
}