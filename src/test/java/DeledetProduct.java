import com.github.javafaker.Faker;
import dto.ProductDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static util.RetrofitUtil.getProductsService;

public class DeledetProduct {
    ProductDto productDto;


    @SneakyThrows
    @Test
    void delete(){
        Response<Void> response = getProductsService().deleteProducts(7)
                .execute();
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body())
                .isEqualTo(productDto);
    }

}
