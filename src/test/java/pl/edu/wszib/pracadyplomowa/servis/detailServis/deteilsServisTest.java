package pl.edu.wszib.pracadyplomowa.servis.detailServis;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.service.DetailsService;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
public class deteilsServisTest {

    DetailsService detailsService;

//    @BeforeEach
//    void Setup(){
//        ProductDetilsDto prod1 = new ProductDetilsDto(1L, "name1", "icon", "picture", "description1", 10.22, 1, 1);
//        detailsService = new DetailsService();
//        detailsService.addToDetails(prod1);
//    }

    @Test
    public void addToDetailsTest(){
        ProductDetilsDto prod1 = new ProductDetilsDto(1L, "name1", "icon", "picture", "description1", 10.22, 1, 1);
        detailsService = new DetailsService();
        detailsService.addToDetails(prod1);
        assertNotNull(detailsService.getById(1L));

    }

    @Test
    public void clearDetailsMapTest() {
        ProductDetilsDto prod2 = new ProductDetilsDto(2L, "name1", "icon", "picture", "description1", 10.22, 1, 1);
        detailsService = new DetailsService();
        detailsService.addToDetails(prod2);
        detailsService.clearDetailsMap();
        assertNull(detailsService.getById(2L));
    }

}
