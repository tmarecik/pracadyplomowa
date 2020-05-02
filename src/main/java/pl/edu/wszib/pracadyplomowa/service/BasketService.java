package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.dto.BasketProduct;
import pl.edu.wszib.pracadyplomowa.dto.ProductBasketMapper;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class BasketService {

    Map<Long, BasketProduct> basketMap = new HashMap<>();
    ProductBasketMapper basketMapper = new ProductBasketMapper();
    ProductDao productDao;
    public Map<Long, Product> productMap = new HashMap<>();

    public BasketService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void addToBasket(BasketProduct basketProduct) {
        basketMap.put(basketProduct.getId(), basketProduct);
    }

    public void addToProductMap(Product product){
        productMap.put(product.getId(), product);
    }

    public Collection<BasketProduct> getBasketProducts(){
        return basketMap.values();
    }

    public BasketProduct getBasketProductById(Long id) {
        return basketMap.get(id);
    }

    public BasketProduct detailsToBasket(ProductDetilsDto product){
       return basketMapper.detailsToBasket(product);
    }

    public void delete(Long id){
        basketMap.remove(id);
        productMap.remove(id);
    }

    public double basketTotalPrice(){
        double basketTotalPrice = 0;
        Set<Map.Entry<Long, BasketProduct>> productSet = basketMap.entrySet();
        for(Map.Entry<Long, BasketProduct> basketProduct: productSet){
            basketTotalPrice = basketTotalPrice + basketProduct.getValue().getTotalPrice();
        }
        return basketTotalPrice;

    }

    public void deleteAll() {
        basketMap.clear();
        productMap.clear();
    }

    public void databaseAvailabilityUpdate(Product product){
        BasketProduct basketProduct = getBasketProductById(product.getId());
        int dBavaibility = product.getAvailability();
        int boughtItems = basketProduct.getAmount();
        int updatedAvailability = dBavaibility - boughtItems;
        if (updatedAvailability >= 0 ){
            product.setAvailability(updatedAvailability);
            productDao.save(product);
        } else {
            System.out.println("there is not enought items in shop store");
        }
    }

    public void buyAllStuff(){
        Set<Map.Entry<Long, Product>> productSet = productMap.entrySet();
        for(Map.Entry<Long, Product> product: productSet){
            databaseAvailabilityUpdate(product.getValue());
        }
    }


}
