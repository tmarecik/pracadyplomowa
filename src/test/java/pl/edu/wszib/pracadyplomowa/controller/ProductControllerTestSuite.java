package pl.edu.wszib.pracadyplomowa.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.wszib.pracadyplomowa.dto.BasketProduct;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.dto.ProductListMemberDto;
import pl.edu.wszib.pracadyplomowa.model.Product;
import pl.edu.wszib.pracadyplomowa.service.BasketService;
import pl.edu.wszib.pracadyplomowa.service.DetailsService;
import pl.edu.wszib.pracadyplomowa.service.ProductService;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTestSuite {

    static byte [] byteArray = new byte[]{0x00, 0x01};

    static private ProductListMemberDto plmd1 = new ProductListMemberDto(1L,"name1", "icon", 11.11 , 1);
    static private ProductListMemberDto plmd2 = new ProductListMemberDto(2L,"name2", "icon", 22.22 , 2);
    static private ProductDetilsDto product = new ProductDetilsDto(1L, "name",  "icon", "picture", "description", 100.00, 2, 2);
    static private Product productProduct = new Product(1L,"name1", byteArray, byteArray, "description1", 11.11, 1);
    static private ProductDetilsDto pdd1 = new ProductDetilsDto(1L, "name",  "icon", "picture", "description", 100.00, 2, 2);

    static private BasketProduct bp1 = new BasketProduct(1L,"name1_bp11", "icon", 100.00, 1, 0.00);
    static private BasketProduct bp2 = new BasketProduct(2L,"name1_bp12", "icon", 200.00, 2, 0.00);

    static private List<ProductListMemberDto> productsListMemberDtoMock = new ArrayList<>();
    static private Map<Long, BasketProduct> basketMapMock = new HashMap<>();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productServiceMock;

    @MockBean
    private DetailsService detailsServiceMock;

    @MockBean
    private BasketService basketServiceMock;

    @Test
    public void shouldInjectControllerContextAndVerifyIfWelcomeViewIsReturn() throws Exception{
        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andDo(print());
    }

    @Test
    public void shouldVerifyIfProductsMethodIsConfigure_AndPassTwo_ProductListMemberDto_Into_ViewProducts() throws Exception{

        productsListMemberDtoMock.add(plmd1);
        productsListMemberDtoMock.add(plmd2);
        assertThat(productsListMemberDtoMock).hasSize(2);
        Mockito.when(productServiceMock.getProducts()).thenReturn(productsListMemberDtoMock);

        mockMvc
                .perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attribute("products", hasSize(2)))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("name1")),
                                hasProperty("icon", is("icon")),
                                hasProperty("price", is(11.11)),
                                hasProperty("availability", is(1))
                        )

                )))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("name2")),
                                hasProperty("icon", is("icon")),
                                hasProperty("price", is(22.22)),
                                hasProperty("availability", is(2))
                        )
                )));
    }

    @Test
    public void shouldVerifyIf_The_GetProduct_Method_takingProductById_and_passingItInto_productDetails() throws Exception{

        Mockito.when(productServiceMock.getById(1L)).thenReturn(pdd1);
        Mockito.when(detailsServiceMock.getById(product.getId())).thenReturn(pdd1);

        mockMvc
                .perform(get("/details/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                        .andExpect(view().name("product-details"))
                        .andExpect(model().attribute("product", hasProperty("id", is(1L))))
                        .andExpect(model().attribute("product", hasProperty("name", is("name"))))
                        .andExpect(model().attribute("product", hasProperty("icon", is("icon"))))
                        .andExpect(model().attribute("product", hasProperty("picture", is("picture"))))
                        .andExpect(model().attribute("product", hasProperty("description", is("description"))))
                        .andExpect(model().attribute("product", hasProperty("price", is(100.00))))
                        .andExpect(model().attribute("product", hasProperty("availability", is(2))))
                        .andExpect(model().attribute("product", hasProperty("amount", is(2))));
    }

    @Test
    public void shouldVerifyIf_The_fromDetailsAddToBasket_Method_afterUpdating_redirectIntoBasketView() throws Exception {

        mockMvc
                .perform(post("/details/update"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/basket"))
                .andExpect(view().name("redirect:/basket"));
    }

    @Test
    public void shouldVerifyIf_The_basketProducts_Method_afterUpdating_redirectIntoBasketView() throws Exception {

        basketMapMock.put(bp1.getId(), bp1);
        basketMapMock.put(bp2.getId(), bp2);
        assertThat(basketMapMock).hasSize(2);

        Mockito.when(basketServiceMock.basketTotalPrice()).thenReturn(256.00);
        Mockito.when(basketServiceMock.basketTotalItems()).thenReturn(1);
        Mockito.when(basketServiceMock.getBasketProducts()).thenReturn(basketMapMock.values());

        mockMvc
                .perform(get("/basket"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("basket"))
                .andExpect(model().attribute("basketTotalPrice", is(256.00)))
                .andExpect(model().attribute("basketTotalItems", is(1)))
                .andExpect(model().attribute("products", hasSize(2)))

                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("icon", is("icon")),
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("name1_bp11")),
                                hasProperty("price", is(100.00)),
                                hasProperty("amount", is(1)),
                                hasProperty("totalPrice", is(0.00))
                )
        )))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("icon", is("icon")),
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("name1_bp12")),
                                hasProperty("price", is(200.00)),
                                hasProperty("amount", is(2)),
                                hasProperty("totalPrice", is(0.00))
                        )
                )));
        }

    @Test
    public void shouldVerifyIfafter_DeleteProductFromBasket_viewIsRedirectInto_basket() throws Exception {

        mockMvc
                .perform(get("/basket/delete/{id}", 1L))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/basket"))
                .andExpect(view().name("redirect:/basket"));
    }

    @Test
    public void shouldVerifyIfafter_DeleteAllProductFromBasket_viewIsRedirectInto_basket() throws Exception {

        mockMvc
                .perform(get("/basket/deleteAll"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/basket"))
                .andExpect(view().name("redirect:/basket"));
    }

    @Test
    public void shouldVerifyIfafter_buy_viewIsRedirectInto_products() throws Exception {

        mockMvc
                .perform(get("/basket/buy"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/products"))
                .andExpect(view().name("redirect:/products"));
    }

    @Test
    public void shouldVerifyIfafter_getBasketProduct_BasketProductAndProduct_were_passedIntoModelAndRedirectViewInto_basketProduct() throws Exception {

        Mockito.when(basketServiceMock.getBasketProductById(1L)).thenReturn(bp1);
        Mockito.when(basketServiceMock.getProductFromProductMapById(1L)).thenReturn(productProduct);

        mockMvc
            .perform(get("/basket/{id}", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("basketProduct"))
                .andExpect(model().attribute("basketProduct", hasProperty("id", is(1L))))
                .andExpect(model().attribute("basketProduct", hasProperty("name", is("name1_bp11"))))
                .andExpect(model().attribute("basketProduct", hasProperty("icon", is("icon"))))
                .andExpect(model().attribute("basketProduct", hasProperty("price", is(100.00))))
                .andExpect(model().attribute("basketProduct", hasProperty("amount", is(1))))
                .andExpect(model().attribute("basketProduct", hasProperty("totalPrice", is(0.00))))

                .andExpect(model().attribute("dBproduct", hasProperty("id", is(1L))))
                .andExpect(model().attribute("dBproduct", hasProperty("name", is("name1"))))
                .andExpect(model().attribute("dBproduct", hasProperty("picture", is(byteArray))))
                .andExpect(model().attribute("dBproduct", hasProperty("icon", is(byteArray))))
                .andExpect(model().attribute("dBproduct", hasProperty("description", is("description1"))))
                .andExpect(model().attribute("dBproduct", hasProperty("price", is(11.11))))
                .andExpect(model().attribute("dBproduct", hasProperty("availability", is(1))));

    }
}
