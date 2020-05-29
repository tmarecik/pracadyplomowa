package pl.edu.wszib.pracadyplomowa.servis;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.dto.BasketProduct;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.model.Product;
import pl.edu.wszib.pracadyplomowa.service.BasketService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    static private byte [] byteArray = new byte[]{0x00, 0x01};
    static private String icon = "icon";

    static private Product p1 = new Product(1L,"name1", byteArray, byteArray, "description1", 11.11, 2);
    static private Product p2 = new Product(2L,"name2", byteArray, byteArray, "description1", 11.11, 2);
    static private BasketProduct bp1 = new BasketProduct(1L,"name1_bp11", icon, 100, 1, 0);
    static private BasketProduct bp2 = new BasketProduct(2L,"name1_bp12", icon, 200, 1, 0);

    static private ProductDetilsDto pdd1 = new ProductDetilsDto(1L, "name",  "icon", "picture", "description", 100.00, 2, 2);

    private ProductDao productDao = Mockito.mock(ProductDao.class);

    private BasketService basketService = new BasketService(productDao);


    @AfterEach
    void tearDown() {
        basketService.deleteAll();
    }

    @Test
    public void  shouldNotRiseNullPointerException_whenTryToAddBasketProduct_addToBasketTest(){
        assertThat(basketService.basketMap).hasSize(0);
        Assertions.assertThatCode(() -> basketService.addToBasket(bp1))
                .doesNotThrowAnyException();
        assertThat(basketService.basketMap).hasSize(1);
    }

    @Test(expected = NullPointerException.class)
    public void  shouldRiseNullPointerException_whenBasketProductEqNULLTryToAdd_addToBasketTest(){
        assertThat(basketService.basketMap).hasSize(0);
        basketService.addToBasket(null);
    }

    @Test
    public void shouldNotRiseNullPointerException_whenTryToAddProduct_addToProductMapTest(){
        assertThat(basketService.productMap).hasSize(0);
        Assertions.assertThatCode(() -> basketService.addToProductMap(p1))
                .doesNotThrowAnyException();
        assertThat(basketService.productMap).hasSize(1);
    }


    @Test(expected = NullPointerException.class)
    public void shouldRiseNullPointerException_whenTryToAddNullProduct_addToProductMapTest(){
        assertThat(basketService.productMap).hasSize(0);
        basketService.addToProductMap(null);
    }

    @Test
    public void getBasketProductsTest(){
        assertThat(basketService.basketMap).hasSize(0);
        basketService.basketMap.put(bp1.getId(), bp1);
        basketService.basketMap.put(bp2.getId(), bp1);
        assertThat(basketService.basketMap).hasSize(2);
        Collection basketProductsCollection = basketService.getBasketProducts();
        assertThat(basketProductsCollection).hasSize(2);
    }

    @Test
    public void getBasketProductByIdTest(){
        assertThat(basketService.basketMap).hasSize(0);
        basketService.basketMap.put(bp1.getId(), bp1);
        basketService.basketMap.put(bp2.getId(), bp2);
        Long id = 2L;
        BasketProduct bp2_id12 = basketService.getBasketProductById(id);
        assertEquals(bp2_id12.getId(), 2L);
    }

    @Test
    public void getProductFromProductMapByIdTest(){
        assertThat(basketService.productMap).hasSize(0);
        basketService.productMap.put(p1.getId(), p1);
        basketService.productMap.put(p2.getId(), p2);
        Long id = 2L;
        Product p2_id2 = basketService.getProductFromProductMapById(id);
        assertEquals(p2_id2.getId(), 2L);
    }

    @Test
    public void detailsToBasketTest(){
        BasketProduct bp_transform = basketService.detailsToBasket(pdd1);
        assertThat((bp_transform != null));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        assertThat(basketService.basketMap).hasSize(0);
        assertThat(basketService.productMap).hasSize(0);

        basketService.basketMap.put(bp1.getId(), bp1);
        basketService.basketMap.put(bp2.getId(), bp2);
        basketService.productMap.put(p1.getId(), p1);
        basketService.productMap.put(p2.getId(), p2);

        assertThat(basketService.basketMap).hasSize(2);
        assertThat(basketService.productMap).hasSize(2);

        basketService.delete(id);

        assertThat(basketService.basketMap).hasSize(1);
        assertThat(basketService.productMap).hasSize(1);

        BasketProduct pb_from_map = basketService.basketMap.get(2L);
        Product p_from_map = basketService.productMap.get(2L);

        assertEquals(pb_from_map.getId(), 2L);
        assertEquals(p_from_map.getId(), 2L);
    }

    @Test
    public void deleteAllTest(){
        assertThat(basketService.basketMap).hasSize(0);
        assertThat(basketService.productMap).hasSize(0);
        basketService.basketMap.put(bp1.getId(), bp1);
        basketService.productMap.put(p1.getId(), p1);
        assertThat(basketService.basketMap).hasSize(1);
        assertThat(basketService.productMap).hasSize(1);
        basketService.deleteAll();
        assertThat(basketService.basketMap).hasSize(0);
        assertThat(basketService.productMap).hasSize(0);
    }

    @Test
    public void basketTotalPriceTest(){

        basketService.basketMap.put(bp1.getId(), bp1);
        basketService.basketMap.put(bp2.getId(), bp2);
        assertThat(basketService.basketMap).hasSize(2);

        bp1.setTotalPrice();
        bp2.setTotalPrice();

        double totalPrice = basketService.basketTotalPrice();
        assertEquals(totalPrice, 300.00);

        bp2.setAmount(2);
        bp2.setTotalPrice();

        totalPrice = basketService.basketTotalPrice();
        assertEquals(totalPrice, 500.00);

    }

    @Test
    public void basketTotalItemsTest(){
        assertThat(basketService.basketMap).hasSize(0);
        assertThat(basketService.productMap).hasSize(0);

        basketService.basketMap.put(bp1.getId(), bp1);
        basketService.basketMap.put(bp2.getId(), bp2);

        assertThat(basketService.basketMap).hasSize(2);
        assertThat(basketService.productMap).hasSize(0);

        bp1.setAmount(1);
        bp2.setAmount(1);

        double itemsTotalNumber = basketService.basketTotalItems();
        assertEquals(itemsTotalNumber, 2);

        bp1.setAmount(3);
        bp2.setAmount(2);

        itemsTotalNumber = basketService.basketTotalItems();
        assertEquals(itemsTotalNumber, 5);
    }

    @Test
    public void  databaseAvailabilityUpdateTest() throws Exception{

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        basketService.basketMap.put(bp1.getId(), bp1);
        assertEquals(p1.getAvailability(), 2);

        bp1.setAmount(2);
        basketService.databaseAvailabilityUpdate(p1);
        assertEquals(p1.getAvailability(), 0);

        bp1.setAmount(1);
        basketService.databaseAvailabilityUpdate(p1);
        String specifiedValue  = "there is not enought items in shop store";
        String actualValue = outContent.toString();
        assertThat(actualValue, containsString(specifiedValue));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRiseIllegalArgumentException_ifTotalItemsInBasketIsZero_buyAllStuffTest() throws Exception{
        assertThat(basketService.basketMap).hasSize(0);
        basketService.buyAllStuff();
    }

    @Test
    public void shouldNotRiseIllegalArgumentException_ifTotalItemsInBasketIsNONZero_buyAllStuffTest() throws Exception{
        assertThat(basketService.basketMap).hasSize(0);
        basketService.basketMap.put(bp1.getId(), bp1);
        assertThat(basketService.basketMap).hasSize(1);

        basketService.buyAllStuff();
        Assertions.assertThatCode(() -> basketService.buyAllStuff())
                .doesNotThrowAnyException();
    }

}


