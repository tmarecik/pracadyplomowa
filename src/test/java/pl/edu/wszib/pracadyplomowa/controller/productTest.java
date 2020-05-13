package pl.edu.wszib.pracadyplomowa.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.dto.ProductListMemberDto;
import pl.edu.wszib.pracadyplomowa.service.DetailsService;
import pl.edu.wszib.pracadyplomowa.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class productTest {

    @Mock
    ProductDao productDao;

    ProductService productService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        productService = Mockito.spy(new ProductService(productDao));
    }

    @Mock
    DetailsService detailsService;

    @InjectMocks
    ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController, productService, detailsService).build();
    }

    @Test(expected = NullPointerException.class)
    public void productsTest() throws Exception{

        ProductListMemberDto product1 = new ProductListMemberDto(1L, "gitara", "icon", 112.00, 1 );
        ProductListMemberDto product2 = new ProductListMemberDto(2L, "gitara", "icon", 112.00, 1 );
        List<ProductListMemberDto> productsListDto = new ArrayList<>();
        productsListDto.add(product1);
        productsListDto.add(product2);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products.html"))
                .andExpect(model().attribute("products", hasSize(2)));
    }
}