package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FileSuffixTest {

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
    public void returnsOneForTextTxt() {
        given().when().get("/api/pat/{txt}", "arrange-text").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "file.txt");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void returnsTwoForAcrobatPdf() {
        given().when().get("/api/pat/{txt}", "arrange-acrobat").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "acrobat", "report.pdf");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void returnsThreeForWordDoc() {
        given().when().get("/api/pat/{txt}", "arrange-word").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "word", "document.doc");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void returnsFourForBinExeWithMultipleDots() {
        given().when().get("/api/pat/{txt}", "arrange-bin").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "bin", "app.tar.exe");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void returnsFiveForLibDll() {
        given().when().get("/api/pat/{txt}", "arrange-lib").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "lib", "library.dll");
        act.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void returnsZeroWhenNoSuffixPresent() {
        given().when().get("/api/pat/{txt}", "arrange-nosuffix").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/filesuffix/{directory}/{file}", "text", "file");
        act.then().body(equalTo("0"));
    }
}