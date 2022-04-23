import com.github.javafaker.Faker;
import dto.ProductDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.http.Body;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static util.RetrofitUtil.getCategoryService;
import static util.RetrofitUtil.getProductsService;


public class changeProductTest {
    ProductDto productDto;
    int productId;


    @SneakyThrows
    @BeforeEach
    void setUp() {
        productDto = new ProductDto()
                .withCategoryTitle(Objects.requireNonNull(getCategoryService().getCategory(1).execute().body()).getTitle())
                .withTitle(new Faker().food().ingredient())
                .withPrice((int) (Math.random() * 10000));
    }

    @SneakyThrows
    @Test
    void changeProductTest(){
        Call<ProductDto> changeProducts = getProductsService().changeProducts(1, productDto);
        changeProducts.execute();
    }

}