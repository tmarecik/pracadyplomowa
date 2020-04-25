package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetailsMapper;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.dto.ProductListMemberDto;
import pl.edu.wszib.pracadyplomowa.dto.ProductListMemeberMapper;
import pl.edu.wszib.pracadyplomowa.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductDao productDao;
//    Map<Long, Product> productMap;


    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
//        this.productMap = new HashMap<>();
        fillDB();
    }

    public List<ProductListMemberDto> getProducts() {
        List<Product> productDaoList = productDao.findAll();
        List<ProductListMemberDto> productsListDto = new ArrayList<>();
        for(Product product: productDaoList){
            productsListDto.add(ProductListMemeberMapper.DaoToDto(product));
        }
        return productsListDto;
    }

    public ProductDetilsDto getById(Long id){
        Product product = productDao.getById(id);
        ProductDetilsDto detailsDto;
        detailsDto = ProductDetailsMapper.DaoToDto(product);
//        detailsService.addToDetailsMap(detailsDto);
        return detailsDto;
    }
    
    
    private void fillDB(){
        byte[] picture = new byte[0];
        byte[] icon = new byte[0];
        String desc = new String();
        try {
            picture = Files.readAllBytes(Paths.get(".\\src\\main\\java\\samic.jpg"));
            icon = Files.readAllBytes(Paths.get(".\\src\\main\\java\\samic_icon.jpg"));
            desc = new String(Files.readAllBytes(Paths.get(".\\src\\\\main\\\\java\\\\samic_description.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product("Samic", picture, icon, desc, 1240, 1 );
        Product product1 = new Product("Epiphone_SG100", picture, icon, "epiphone SG100 ", 8900, 1 );
        Product product2 = new Product("Gibson_Les_Pole", picture, icon, "Gibson Les Pole", 15200, 12 );

        productDao.save(product);
        productDao.save(product1);
        productDao.save(product2);
    }
}
