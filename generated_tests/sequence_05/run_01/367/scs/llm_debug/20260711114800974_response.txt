package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FileSuffixTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return envUrl != null ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithNonTxtFile() {
        given().get(getBaseUrl() + "/api/filesuffix/text/file.pdf").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAcrobatDirectoryWithNonPdfFile() {
        given().get(getBaseUrl() + "/api/filesuffix/acrobat/file.txt").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordDirectoryWithNonDocFile() {
        given().get(getBaseUrl() + "/api/filesuffix/word/file.txt").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinDirectoryWithNonExeFile() {
        given().get(getBaseUrl() + "/api/filesuffix/bin/file.txt").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLibDirectoryWithNonDllFile() {
        given().get(getBaseUrl() + "/api/filesuffix/lib/file.txt").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTextDirectoryWithTxtFile() {
        given().get(getBaseUrl() + "/api/filesuffix/text/file.txt").then().statusCode(200);
    }
}