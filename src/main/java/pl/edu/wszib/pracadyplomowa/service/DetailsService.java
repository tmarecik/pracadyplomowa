package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class DetailsService {

    Map<Long, ProductDetilsDto> detailsMap = new HashMap<>();

    public void addToDetails(ProductDetilsDto productDetilsDto) {
        detailsMap.put(productDetilsDto.getId(), productDetilsDto);
    }

    public void update(ProductDetilsDto activeProduct){
        ProductDetilsDto product = detailsMap.get(activeProduct.getId());
        product.setId(activeProduct.getId());
        product.setName(activeProduct.getName());
        product.setIcon(activeProduct.getIcon());
        product.setPicture(activeProduct.getPicture());
        product.setPrice(activeProduct.getPrice());
        product.setAvailability(activeProduct.getAvailability());
        product.setAmount(activeProduct.getAmount());
//        product.setAmount(2);
        detailsMap.replace(product.getId(), product);
    }

    public void clearDetailsMap() {
        detailsMap.clear();
    }

    public ProductDetilsDto getById(Long id){
        return detailsMap.get(id);
    }

}
