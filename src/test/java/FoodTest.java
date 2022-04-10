import extensions.SpoonApiTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Map;

import static io.restassured.RestAssured.given;

@SpoonApiTest
public class FoodTest {


    @ParameterizedTest
    @ValueSource(strings = {"Pizza", "Sushi"})
    public void foodSearchTest(String queryParameter) {
        given()
                .queryParams(Map.of("query", queryParameter,
                        "offset", 0,
                        "number", 10))
                .get("/food/search")
                .then()
                .statusCode(200)
                .body("query", Matchers.equalTo(queryParameter));
    }
}