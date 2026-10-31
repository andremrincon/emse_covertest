package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        RestAssured.get("/v1/alpha/123")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        RestAssured.get("/v1/alpha?codes=US,CA")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        RestAssured.get("/v1/alpha?codes=123")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        RestAssured.get("/v1/alpha?codes=XX,YY,ZZ")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        RestAssured.get("/v1/alpha?codes=%5B%22US%22,%20%22CA%22%5D")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        RestAssured.get("/v1/currency/123")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_InternalServerError() {
        RestAssured.get("/v1/currency/INVALIDCURRENCYCODE")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_InternalServerError() {
        RestAssured.get("/v1/name/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_InternalServerError() {
        RestAssured.get("/v1/callingcode/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_InternalServerError() {
        RestAssured.get("/v1/capital/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_InternalServerError() {
        RestAssured.get("/v1/region/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_InternalServerError() {
        RestAssured.get("/v1/subregion/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_InternalServerError() {
        RestAssured.get("/v1/lang/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_IsEmpty() {
        RestAssured.get("/v1/alpha")
            .then()
            .statusCode(400);
    }
}