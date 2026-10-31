package ts01glm_5_2_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    private static final String BASE_URL = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));

    @Test(timeout = 60000)
    public void testV2AlphaByCode() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("alphacode", "US").when().get(BASE_URL + "/v2/alpha/{alphacode}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Name() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("name", "Germany").when().get(BASE_URL + "/v2/name/{name}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Lang() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("lang", "es").when().get(BASE_URL + "/v2/lang/{lang}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Capital() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("capital", "Paris").when().get(BASE_URL + "/v2/capital/{capital}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Region() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("region", "Europe").when().get(BASE_URL + "/v2/region/{region}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Subregion() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("subregion", "Western%20Europe").when().get(BASE_URL + "/v2/subregion/{subregion}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Callingcode() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("callingcode", "1").when().get(BASE_URL + "/v2/callingcode/{callingcode}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Currency() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("currency", "EUR").when().get(BASE_URL + "/v2/currency/{currency}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Demonym() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("demonym", "American").when().get(BASE_URL + "/v2/demonym/{demonym}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Regionalbloc() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("regionalbloc", "EU").when().get(BASE_URL + "/v2/regionalbloc/{regionalbloc}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AllCountries() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE_URL + "/v2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Lang() {
        given().when().get(BASE_URL + "/v2/all").then().statusCode(lessThan(300));
        given().pathParam("lang", "es").when().get(BASE_URL + "/v1/lang/{lang}").then().statusCode(200);
    }
}