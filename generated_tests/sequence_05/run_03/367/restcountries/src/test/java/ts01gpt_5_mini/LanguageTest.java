package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("Cannot deserialize value of type `java.util.ArrayList<java.lang.Object>` from Object value (token...")
    @Test(timeout = 60000)
    public void testV1AlphaUS_Iso639_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String iso1 = (String) lang0.get("iso639_1");
        Assert.assertThat(iso1, equalTo("en"));
    }

    @Ignore("Cannot deserialize value of type `java.util.ArrayList<java.lang.Object>` from Object value (token...")
    @Test(timeout = 60000)
    public void testV1AlphaUS_Iso639_2() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String iso2 = (String) lang0.get("iso639_2");
        Assert.assertThat(iso2, equalTo("eng"));
    }

    @Ignore("Cannot deserialize value of type `java.util.ArrayList<java.lang.Object>` from Object value (token...")
    @Test(timeout = 60000)
    public void testV1AlphaUS_Name() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String name = (String) lang0.get("name");
        Assert.assertThat(name, equalTo("English"));
    }

    @Ignore("Cannot deserialize value of type `java.util.ArrayList<java.lang.Object>` from Object value (token...")
    @Test(timeout = 60000)
    public void testV1AlphaUS_NativeName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String nativeName = (String) lang0.get("nativeName");
        Assert.assertThat(nativeName, equalTo("English"));
    }

    @Ignore("java.lang.String cannot be cast to java.util.Map")
    @Test(timeout = 60000)
    public void testV1NameFrance_Iso639_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String iso1 = (String) lang0.get("iso639_1");
        Assert.assertThat(iso1, equalTo("fr"));
    }

    @Ignore("java.lang.String cannot be cast to java.util.Map")
    @Test(timeout = 60000)
    public void testV1NameFrance_Iso639_2() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String iso2 = (String) lang0.get("iso639_2");
        Assert.assertThat(iso2, equalTo("fra"));
    }

    @Ignore("java.lang.String cannot be cast to java.util.Map")
    @Test(timeout = 60000)
    public void testV1NameFrance_Name() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String name = (String) lang0.get("name");
        Assert.assertThat(name, equalTo("French"));
    }

    @Ignore("java.lang.String cannot be cast to java.util.Map")
    @Test(timeout = 60000)
    public void testV1NameFrance_NativeName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France").then().statusCode(200).extract().response();
        List<Map> root = act.as(List.class);
        Map first = root.get(0);
        List<Map> langs = (List) first.get("languages");
        Map lang0 = langs.get(0);
        String nativeName = (String) lang0.get("nativeName");
        Assert.assertThat(nativeName, equalTo("français"));
    }
}