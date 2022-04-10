import extensions.SpoonApiTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Map;

import static io.restassured.RestAssured.given;

@SpoonApiTest
public class ComplexSearch {

    @ParameterizedTest
    @ValueSource(strings = {"Cauliflower", "Garlicky Kale"})
    public void complexSearchTest(String queryParameter) {
        given()
                .queryParams(Map.of("query", queryParameter,
                        "offset", 0,
                        "number", 10))
                .get("/recipes/complexSearch")
                .then()
                .statusCode(200);

    }
}