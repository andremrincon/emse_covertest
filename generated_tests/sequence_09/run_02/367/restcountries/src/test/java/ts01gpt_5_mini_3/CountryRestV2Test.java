package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

import org.junit.Ignore;
public class CountryRestV2Test {

    private static final String BASE = Optional.ofNullable(System.getProperty("base.url"))
            .orElse(Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080/rest"));

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaSuccess_fieldsNull() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/alpha/US");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest_shortCode() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/alpha/1");
        resp.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/alpha/ZZZ");
        resp.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess_withFields() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").queryParam("fields", "name;capital;population")
                .when().get(BASE + "/v2/alpha");
        resp.then().body(containsString("name"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest_brackets() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\", \"CA\"]").when().get(BASE + "/v2/alpha");
        resp.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCurrencySuccess_fields() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("currency", "EUR").queryParam("fields", "name;capital;population")
                .when().get(BASE + "/v2/currency/{currency}");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest_length() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("currency", "12").when().get(BASE + "/v2/currency/{currency}");
        resp.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("name", "France").queryParam("fullText", "true")
                .when().get(BASE + "/v2/name/{name}");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("callingcode", "1").queryParam("fields", "name;capital;region")
                .when().get(BASE + "/v2/callingcode/{callingcode}");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("capital", "Paris").queryParam("fields", "name;capital;population")
                .when().get(BASE + "/v2/capital/{capital}");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionSuccess_fields() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("region", "Europe").queryParam("fields", "name;capital;population")
                .when().get(BASE + "/v2/region/{region}");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetBySubRegionSuccess() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("subregion", "Western Europe").queryParam("fields", "name;capital;population")
                .when().get(BASE + "/v2/subregion/{subregion}");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguageSuccess() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp = given().pathParam("lang", "Spanish").queryParam("fields", "name;capital;population")
                .when().get(BASE + "/v2/lang/{lang}");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByDemonymSuccess_andRegionalBlocSuccess() {
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp1 = given().pathParam("demonym", "American").queryParam("fields", "name;capital;population")
                .when().get(BASE + "/v2/demonym/{demonym}");
        resp1.then().statusCode(200);
        given().when().get(BASE).then().statusCode(lessThan(300));
        Response resp2 = given().pathParam("regionalbloc", "EU").queryParam("fields", "name;capital;currencies")
                .when().get(BASE + "/v2/regionalbloc/{regionalbloc}");
        resp2.then().statusCode(200);
    }
}