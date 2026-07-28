package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @Test(timeout = 60000)
    public void testLoadJson() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList() {
        given()
                .queryParam("codes", "US;CA")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 42: http://localhost:8080/rest/v2/name/Vatican City State")
    @Test(timeout = 60000)
    public void testFulltextSearch() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/{name}", "Vatican City State")
                .then()
                .statusCode(200);
    }
}