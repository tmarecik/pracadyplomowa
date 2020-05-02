package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.model.Product;

import java.util.HashMap;
import java.util.Map;

@Service
public class DetailsService {

    Map<Long, ProductDetilsDto> detailsMap = new HashMap<>();
    Map<Long, Product> productMap = new HashMap<>();

    public void addToDetails(ProductDetilsDto productDetilsDto) {
        detailsMap.put(productDetilsDto.getId(), productDetilsDto);

    }

//    public void addToDetails(Product product) {
//        detailsMap.put(product.getId(), product);
//    }

    public void clearDetailsMap() {
        detailsMap.clear();
    }

    public ProductDetilsDto getById(Long id){
        ProductDetilsDto getProduct = detailsMap.get(id);
        return getProduct;
    }

//    public Product getById(Long id){
//        Product getProduct = detailsMap.get(id);
//        return getProduct;
//    }

}
