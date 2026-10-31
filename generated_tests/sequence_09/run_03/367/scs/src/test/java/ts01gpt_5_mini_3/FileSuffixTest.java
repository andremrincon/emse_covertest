package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FileSuffixTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtReturns1() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/notes.txt");
        assertEquals("1", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithPdfReturns2() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/acrobat/document.pdf");
        assertEquals("2", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithDocReturns3() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/word/report.doc");
        assertEquals("3", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithExeReturns4() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/bin/installer.exe");
        assertEquals("4", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithDllReturns5() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/lib/library.dll");
        assertEquals("5", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testFileWithNoSuffixReturns0() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/filesuffix/text/README");
        assertEquals("0", resp.asString().trim());
    }
}