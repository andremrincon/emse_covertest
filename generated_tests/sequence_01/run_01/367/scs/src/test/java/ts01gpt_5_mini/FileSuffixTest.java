package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {
    private static final String BASE = Optional.ofNullable(System.getProperty("baseUrl")).orElse(Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080"));

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() {
        UUID uuid = UUID.randomUUID();
        given().when().get(BASE + "/api/pat/" + uuid.toString()).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/filesuffix/text/sample.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() {
        UUID uuid = UUID.randomUUID();
        given().when().get(BASE + "/api/pat/" + uuid.toString()).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/filesuffix/acrobat/report.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() {
        UUID uuid = UUID.randomUUID();
        given().when().get(BASE + "/api/pat/" + uuid.toString()).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/filesuffix/word/manual.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() {
        UUID uuid = UUID.randomUUID();
        given().when().get(BASE + "/api/pat/" + uuid.toString()).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/filesuffix/bin/program.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() {
        UUID uuid = UUID.randomUUID();
        given().when().get(BASE + "/api/pat/" + uuid.toString()).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/filesuffix/lib/native.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testNoSuffixReturns0() {
        UUID uuid = UUID.randomUUID();
        given().when().get(BASE + "/api/pat/" + uuid.toString()).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/filesuffix/anyfolder/filename");
        act.then().body(equalTo("0"));
    }
}