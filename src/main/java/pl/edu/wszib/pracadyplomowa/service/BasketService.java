package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class BasketService {

    Map<Long, ProductDetilsDto> basketMap = new HashMap<>();

    public void addToBasket(ProductDetilsDto productDetilsDto) {
        basketMap.put(productDetilsDto.getId(), productDetilsDto);
    }

    public Map<Long, ProductDetilsDto> getBasketProducts(){
        return basketMap;
    }
}
