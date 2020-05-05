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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class dbtest {

    public final byte[] picture = Files.readAllBytes(Paths.get("C:\\Users\\Tomek\\JAVA\\pracadyplomowa\\src\\test\\java\\pl\\edu\\wszib\\pracadyplomowa\\under_construction.png"));
    public final byte[] icon = Files.readAllBytes(Paths.get("C:\\Users\\Tomek\\JAVA\\pracadyplomowa\\src\\test\\java\\pl\\edu\\wszib\\pracadyplomowa\\under_construction.png"));
    public static final int COUNTER = 2;

    @Autowired
    ProductDao productDao;

    public dbtest() throws IOException {
    }

    @BeforeEach
    void setUp(){

        Product product = new Product("gitara", picture, icon, "ala m kota", 112, 1 );
        Product product1 = new Product("gitara1", picture, icon, "ala m kota", 111, 0 );

        assertNull(product.getId());
        productDao.save(product);
        productDao.save(product1);
        assertNotNull(product.getId());

        List productDB = productDao.findAll();
        for (Object prod: productDB) {
            System.out.println(prod.getClass().getName());
        }
    }

    @AfterEach
    void tearDown() {
        productDao.deleteAll();
    }

    @Test
    void testFetchProduct() {
        Product productDB = productDao.getById(2L);
        assertNotNull(productDB);
        assertEquals(productDB.getId(), productDB.getId());
    }

    @Test
    void testFetchAllProduct() {
        List productDB = productDao.findAll();
        assertNotNull(productDB);
        assertEquals(productDB.size(),2);
    }

}
