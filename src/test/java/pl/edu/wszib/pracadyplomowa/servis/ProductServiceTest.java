package pl.edu.wszib.pracadyplomowa.servis;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.dto.ProductListMemberDto;
import pl.edu.wszib.pracadyplomowa.model.Product;
import pl.edu.wszib.pracadyplomowa.service.ProductService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;


//@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductDao productDao = Mockito.mock(ProductDao.class);;

    private ProductService productService = new ProductService(productDao);

    static byte [] byteArray = new byte[]{0x00, 0x01};

    static private Product p1 = new Product(1L,"name1", byteArray, byteArray, "description1", 11.11, 1);
    static private Product p2 = new Product(2L,"name2", byteArray, byteArray, "description2", 22.22, 2);

    static private List<Product> productDaoList = new ArrayList<>();


    @Test
    public void getProductTest(){

        productDaoList.add(p1);
        productDaoList.add(p2);
        assertEquals(2, productDaoList.size());

        when(productDao.findAll()).thenReturn(productDaoList);
        List<ProductListMemberDto> plmd = productService.getProducts();
        assertThat(plmd).hasSize(2);
    }

    @Test
    public void getByIdTest(){
        when(productDao.getById(1L)).thenReturn(p1);
        ProductDetilsDto pdd = productService.getById(1L);
        assertEquals(1L, pdd.getId());
    }

}
