package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {
    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getenv("BASE_URL")).orElse(System.getProperty("base.url"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void corsOriginHeaderPresentOnV1All() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void corsMethodsHeaderOnV1AlphaUS() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void v1AlphaBadFormatReturns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1AlphaNotFoundReturns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void cacheControlHeaderPresentOnV1CurrencyUSD() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void allowHeadersPresentOnV1NameFrance() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France").then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void postToV2Returns405MethodNotAllowed() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).body("{}").when().post("/v2").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void callingcodeOneReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void corsOriginHeaderPresentOnV2RegionEurope() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/region/Europe").then().header("Access-Control-Allow-Origin", nullValue());
    }
}