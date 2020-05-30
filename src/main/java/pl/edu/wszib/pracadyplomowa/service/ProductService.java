package pl.edu.wszib.pracadyplomowa.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.pracadyplomowa.dao.ProductDao;
import pl.edu.wszib.pracadyplomowa.dto.*;
import pl.edu.wszib.pracadyplomowa.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
//        fillDB();
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
        return detailsDto;
    }

    public Product getProductDaoById(Long id){
        return productDao.getById(id);
    }

//    private void fillDB(){
//        byte[] picture_samic = new byte[0];
//        byte[] icon_samic = new byte[0];
//
//        byte[] picture_RL3 = new byte[0];
//        byte[] icon_RL3 = new byte[0];
//
//        byte[] picture_ltd_ec_1000  = new byte[0];
//        byte[] icon_ltd_ec_1000 = new byte[0];
//
//        byte[] picture_epiphone_LP = new byte[0];
//        byte[] icon_epiphone_LP = new byte[0];
//
//        byte[] picture_gibson_LPS = new byte[0];
//        byte[] icon_gibson_LPS = new byte[0];
//
//        byte[] picture_LTD_snakebyte = new byte[0];
//        byte[] icon_LTD_snakebyte = new byte[0];
//
//        byte[] picture_yamaha_pacyfica112 = new byte[0];
//        byte[] icon_yamaha_pacyfica112 = new byte[0];
//
//        String desc_samic = new String();
//        String desc_ltd_ec_1000 = new String();
//        String desc_RL3 = new String();
//        String desc_epiphone_LP = new String();
//        String desc_gibson_LPS = new String();
//        String desc_LTD_snakebyte = new String();
//        String desc_yamaha_pacyfica112 = new String();
//
//        try {
//            picture_samic = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\samic\\IC1\\SGE_SAM_IC1BK_211335_3.jpg"));
//            icon_samic = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\samic\\IC1\\samic_icon.jpg"));
//            desc_samic = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\samic\\IC1\\SGE_SAM_IC1BK_211335_3.html")));
//
//            picture_ltd_ec_1000 = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\ltd_ec1000\\ltd-ec-1000-vbd.jpg"));
//            icon_ltd_ec_1000 = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\ltd_ec1000\\ltd_ec_1000_icon.jpg"));
//            desc_ltd_ec_1000 = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\ltd_ec1000\\ltd-ec-1000-vbd.html")));
//
//            picture_RL3 = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\RL3\\RL3.jpg"));
//            icon_RL3 = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\RL3\\royale_RL3_icon.jpg"));
//            desc_RL3 = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\RL3\\RL3_opis.html")));
//
//            picture_epiphone_LP = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\epiphone_LP\\Les_Paul_Studio_LT.jpg"));
//            icon_epiphone_LP = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\epiphone_LP\\Les_Paul_Studio_LT_icon.jpg"));
//            desc_epiphone_LP = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\epiphone_LP\\Les_Paul_Studio_LT.html")));
//
//            picture_gibson_LPS = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\gibson_LPS_cherry\\gibson-les-paul-standard-heritage-cherry-sunburst-10109188.jpg"));
//            icon_gibson_LPS = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\gibson_LPS_cherry\\gibson-les-paul-standard-heritage-cherry-sunburst_icon.jpg"));
//            desc_gibson_LPS = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\gibson_LPS_cherry\\gibson-les-paul-standard-heritage-cherry-sunburst-10109188.html")));
//
//            picture_LTD_snakebyte = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\ltd_snakebyte\\ltdj.jpg"));
//            icon_LTD_snakebyte = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\ltd_snakebyte\\ltd_iconj.jpg"));
//            desc_LTD_snakebyte = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\ltd_snakebyte\\ltd.html")));
//
//            picture_yamaha_pacyfica112 = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\yamaha_pacyfica\\i-yamaha-pacifica-112v-ovs.jpg"));
//            icon_yamaha_pacyfica112 = Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\yamaha_pacyfica\\yamacha_pac_112_icon.jpg"));
//            desc_yamaha_pacyfica112 = new String(Files.readAllBytes(Paths.get(".\\src\\main\\resources\\static\\img\\database_data\\yamaha_pacyfica\\yamaha_pacyfica_112.html")));
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Product samic_IC1BK = new Product("Samic IC1BK by Greg Benetti", picture_samic, icon_samic, desc_samic, 980, 2 );
//        Product epiphone_LP = new Product("Epiphone Les Paul Studio LT Vintage Sunburst", picture_epiphone_LP, icon_epiphone_LP, desc_epiphone_LP, 880, 2);
//        Product gibson_LPS_Cherry = new Product("Gibson Les Paul Standard Heritage Cherry Sunburst", picture_gibson_LPS, icon_gibson_LPS, desc_gibson_LPS, 15200, 1 );
//        Product ltd_ec_1000 = new Product("LTD ec 1000", picture_ltd_ec_1000, icon_ltd_ec_1000, desc_ltd_ec_1000, 4800, 1 );
//        Product RL3 = new Product("Greg Bennett Royale RL-3 Electric Guitar", picture_RL3, icon_RL3, desc_RL3, 1480, 3 );
//        Product LTD_snakebyte = new Product("ESP LTD Snakebyte SW", picture_LTD_snakebyte, icon_LTD_snakebyte, desc_LTD_snakebyte, 5890, 1 );
//        Product yamacha_pacyfica112 = new Product("Yamaha Pacyfica 112V OVS", picture_yamaha_pacyfica112, icon_yamaha_pacyfica112, desc_yamaha_pacyfica112, 1409.00, 5 );
//
//        productDao.save(gibson_LPS_Cherry);
//        productDao.save(epiphone_LP);
//        productDao.save(LTD_snakebyte);
//        productDao.save(samic_IC1BK);
//        productDao.save(yamacha_pacyfica112);
//        productDao.save(ltd_ec_1000);
//        productDao.save(RL3);
//
//    }
}
