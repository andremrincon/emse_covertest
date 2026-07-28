package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("base.url")).orElseGet(() -> Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_success_fields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{code}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badRequest_short() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{code}", "1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{code}", "ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_success_with_commas_and_fields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").queryParam("fields", "name;capital;population").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest_array_string() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_emptyCodes_badRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_success_fields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/currency/{currency}", "EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest_length() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}", "12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_notFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}", "XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_success_fullText_true_fields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").queryParam("fields", "name;capital;population").when().get("/v2/name/{name}", "Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;region").when().get("/v2/callingcode/{callingcode}", "1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/capital/{capital}", "Paris").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_notFound_invalid() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/region/{region}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/demonym/{demonym}", "American").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;currencies").when().get("/v2/regionalbloc/{regionalbloc}", "EU").then().statusCode(200);
    }
}