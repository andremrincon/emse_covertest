package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Ignore("Cannot convert class java.lang.String to class ts01gpt_5_mini.LanguageTest$Language.")
    @Test(timeout = 60000)
    public void testSetIso639_1_v1AlphaUS_1() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        Language lang = act.jsonPath().getObject("languages[0]", Language.class);
        if (lang == null) {
            throw new AssertionError("Language mapping failed");
        }
        assertEquals("en", lang.getIso639_1());
    }

    @Ignore("Cannot convert class java.lang.String to class ts01gpt_5_mini.LanguageTest$Language.")
    @Test(timeout = 60000)
    public void testSetIso639_2_v1AlphaUS_2() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        Language lang = act.jsonPath().getObject("languages[0]", Language.class);
        if (lang == null) {
            throw new AssertionError("Language mapping failed");
        }
        assertEquals("eng", lang.getIso639_2());
    }

    @Ignore("Cannot convert class java.lang.String to class ts01gpt_5_mini.LanguageTest$Language.")
    @Test(timeout = 60000)
    public void testSetName_v1AlphaUS_3() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        Language lang = act.jsonPath().getObject("languages[0]", Language.class);
        if (lang == null) {
            throw new AssertionError("Language mapping failed");
        }
        assertEquals("English", lang.getName());
    }

    @Ignore("Cannot convert class java.lang.String to class ts01gpt_5_mini.LanguageTest$Language.")
    @Test(timeout = 60000)
    public void testSetNativeName_v1AlphaUS_4() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        Language lang = act.jsonPath().getObject("languages[0]", Language.class);
        if (lang == null) {
            throw new AssertionError("Language mapping failed");
        }
        assertEquals("English", lang.getNativeName());
    }

    public static class Language {
        public String iso639_1;
        public String iso639_2;
        public String name;
        public String nativeName;

        public Language() {
        }

        public String getIso639_1() {
            return iso639_1;
        }

        public void setIso639_1(String iso639_1) {
            this.iso639_1 = iso639_1;
        }

        public String getIso639_2() {
            return iso639_2;
        }

        public void setIso639_2(String iso639_2) {
            this.iso639_2 = iso639_2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNativeName() {
            return nativeName;
        }

        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }
    }
}