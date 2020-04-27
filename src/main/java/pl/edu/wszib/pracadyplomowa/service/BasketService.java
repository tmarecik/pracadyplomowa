package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BasketService {

    Map<Long, ProductDetilsDto> basketMap = new HashMap<>();

    public void addToBasket(ProductDetilsDto productDetilsDto) {
        basketMap.put(productDetilsDto.getId(), productDetilsDto);
    }

    public Collection<ProductDetilsDto> getBasketProducts(){
        return basketMap.values();
    }

    public ProductDetilsDto getProductById(Long id) {
        return basketMap.get(id);
    }

}
