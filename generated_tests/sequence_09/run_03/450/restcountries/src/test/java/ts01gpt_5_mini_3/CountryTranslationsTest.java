package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetDe_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        Assert.assertEquals("Vereinigte Staaten von Amerika", resp.path("translations.de"));
    }

    @Test(timeout = 60000)
    public void testSetEs_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        Assert.assertEquals("Estados Unidos", resp.path("translations.es"));
    }

    @Test(timeout = 60000)
    public void testSetFr_v1Name_France() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        Assert.assertEquals("France", resp.path("[0].translations.fr"));
    }

    @Test(timeout = 60000)
    public void testSetJa_v1Currency_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD").then().statusCode(200).extract().response();
        Assert.assertEquals("アメリカ領サモア", resp.path("[0].translations.ja"));
    }

    @Test(timeout = 60000)
    public void testSetIt_v1Callingcode_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/1").then().statusCode(200).extract().response();
        Assert.assertEquals("Samoa Americane", resp.path("[0].translations.it"));
    }
}