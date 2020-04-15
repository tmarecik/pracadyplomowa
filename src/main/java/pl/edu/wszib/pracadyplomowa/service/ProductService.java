package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    ProductDao productDao;
//    Map<Long, Product> productMap;


    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
//        this.productMap = new HashMap<>();
        fillDB();
    }

    public List<Product> getProducts() {
        List<Product> productList = productDao.findAll();
        return productList;
    }

    private void fillDB(){
        byte[] picture = new byte[0];
        try {
            picture = Files.readAllBytes(Paths.get("C:\\Users\\Tomek\\JAVA\\pracadyplomowa\\src\\test\\java\\pl\\edu\\wszib\\pracadyplomowa\\under_construction.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product("Samic", picture, "samick by greg benetti", 124 );
        Product product1 = new Product("Epiphone_SG100", picture, "epiphone SG100", 890 );
        Product product2 = new Product("Gibson_Les_Pole", picture, "Gibson Les Pole", 152 );

        /*
        * todo poprawic definicje kolumn format ceny -> przyjmuje tylo trzcyfrowe wartości
        *  todo poprawić description -> ma przyjmować LOB'y*/

        productDao.save(product);
        productDao.save(product1);
        productDao.save(product2);
    }
    /*
    * todo
    *  metodę uzupełniającą DB
    *  widoki*/

}
