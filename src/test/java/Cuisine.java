import extensions.SpoonApiTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Map;

import static io.restassured.RestAssured.given;

@SpoonApiTest
public class Cuisine {

    @ParameterizedTest
    @ValueSource(strings = {"Mediterranean", "Italian"})
    public void cuisineTest(String queryParameter) {
        given()
                .queryParams(Map.of("query", queryParameter,
                        "offset", 0,
                        "confidence", 0.0))
                .post("/recipes/cuisine")
                .then()
                .statusCode(200);

    }
}
