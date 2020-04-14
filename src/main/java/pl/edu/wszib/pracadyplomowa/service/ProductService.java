package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.model.Product;

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
    }

    public List<Product> getProducts() {
        List<Product> productList = productDao.findAll();
        return productList;
    }

    /*
    * todo
    *  metodę uzupełniającą DB
    *  widoki*/

}
