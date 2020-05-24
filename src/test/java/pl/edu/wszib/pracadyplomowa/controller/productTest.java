package pl.edu.wszib.pracadyplomowa.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.wszib.pracadyplomowa.dto.ProductListMemberDto;
import pl.edu.wszib.pracadyplomowa.service.DetailsService;
import pl.edu.wszib.pracadyplomowa.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class productTest {

    static private ProductListMemberDto plmd1 = new ProductListMemberDto(1L,"name1", "icon", 11.11 , 1);
    static private ProductListMemberDto plmd2 = new ProductListMemberDto(2L,"name2", "icon", 22.22 , 2);

    static private List<ProductListMemberDto> productsListMemberDtoMock = new ArrayList<>();


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productServiceMock;

    @MockBean
    private DetailsService detailsServiceMock;


//    @BeforeEach
//    public void setUp(){
//        productsListMemberDtoMock.add(plmd1);
//        productsListMemberDtoMock.add(plmd2);
//        assertThat(productsListMemberDtoMock).hasSize(2);
//        Mockito.when(productServiceMock.getProducts()).thenReturn(productsListMemberDtoMock);
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
//    }

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

//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                return "detailsService.clearDetailsMap() method was run";
//            }
//        }).when(detailsServiceMock).clearDetailsMap();
//
        productsListMemberDtoMock.add(plmd1);
        productsListMemberDtoMock.add(plmd2);
        assertThat(productsListMemberDtoMock).hasSize(2);
        Mockito.when(productServiceMock.getProducts()).thenReturn(productsListMemberDtoMock);

        mockMvc
                .perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
//                .andExpect(forwardedUrl("/products/list.jsp"))
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

}
