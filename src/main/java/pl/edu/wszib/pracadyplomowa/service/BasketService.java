package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dto.BasketProduct;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasketService {

    Map<Long, BasketProduct> basketMap = new HashMap<>();

    public void addToBasket(BasketProduct basketProduct) {
        basketMap.put(basketProduct.getId(), basketProduct);
    }

    public Collection<BasketProduct> getBasketProducts(){
        return basketMap.values();
    }

    public BasketProduct getProductById(Long id) {
        return basketMap.get(id);
    }


}
