package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void fileSuffix_text_txt_returns_1() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + id).then().statusCode(lessThan(300));
        String file = "file-" + id + ".txt";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void fileSuffix_acrobat_pdf_returns_2() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + id).then().statusCode(lessThan(300));
        String file = "doc-" + id + ".pdf";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", file);
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void fileSuffix_word_doc_returns_3() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + id).then().statusCode(lessThan(300));
        String file = "letter-" + id + ".doc";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", file);
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void fileSuffix_bin_exe_returns_4() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + id).then().statusCode(lessThan(300));
        String file = "run-" + id + ".exe";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", file);
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void fileSuffix_lib_dll_returns_5() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + id).then().statusCode(lessThan(300));
        String file = "library-" + id + ".dll";
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", file);
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void fileSuffix_no_dot_returns_0() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + id).then().statusCode(lessThan(300));
        String file = "nodot-" + id;
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", file);
        act.then().body(equalTo("0"));
    }
}