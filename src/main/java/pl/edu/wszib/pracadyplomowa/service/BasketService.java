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


    public void addToBasket(BasketProduct basketProduct) {
        basketMap.put(basketProduct.getId(), basketProduct);
    }

    public Collection<BasketProduct> getBasketProducts(){
        return basketMap.values();
    }

    public BasketProduct getProductById(Long id) {
        return basketMap.get(id);
    }

    public BasketProduct detailsToBasket(ProductDetilsDto product){
       return basketMapper.detailsToBasket(product);
    }

    public void delete(Long id){
        basketMap.remove(id);
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
    }

    public void databaseAvailabilityUpdate(BasketProduct basketProduct){
        Long id = basketProduct.getId();
        Product product = productDao.getById(id);
        int dBavaibility = product.getAvailability();
        int boughtItems = basketProduct.getAmount();
        if (dBavaibility - boughtItems >= 0 ){
            product.setAvailability(dBavaibility - boughtItems);
        } else {
            System.out.println("there is not enought items in shop store");
        }
        productDao.save(product);
    }

    public void buyAllStuff(){
        Set<Map.Entry<Long, BasketProduct>> productSet = basketMap.entrySet();
        for(Map.Entry<Long, BasketProduct> basketProduct: productSet){
            databaseAvailabilityUpdate(basketProduct.getValue());
        }
        deleteAll();
    }


}
