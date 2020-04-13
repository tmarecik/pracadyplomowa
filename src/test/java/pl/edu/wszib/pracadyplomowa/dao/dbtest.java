package pl.edu.wszib.pracadyplomowa.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.wszib.pracadyplomowa.model.Product;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class dbtest {

    @Autowired
    ProductDao productDao;

    @BeforeEach
    void setUp() throws IOException {

        byte[] picture = Files.readAllBytes(Paths.get("C:\\Users\\Tomek\\JAVA\\pracadyplomowa\\src\\test\\java\\pl\\edu\\wszib\\pracadyplomowa\\under_construction.png"));
        Product product = new Product("gitara", picture, "ala m kota", 112 );
        System.out.println("----============== PRODUCT ===========----------   "+ product);

        assertNull(product.getId());
        productDao.save(product);
        assertNotNull(product.getId());
    }

    @AfterEach
    void tearDown() {
        productDao.deleteAll();
    }

    @Test
    void testFetchProduct() {
        Product productDB = productDao.getById(1L);
        assertNotNull(productDB);
        assertEquals(productDB.getId(), productDB.getId());
    }
}
