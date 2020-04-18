package pl.edu.wszib.pracadyplomowa.dto;


import pl.edu.wszib.pracadyplomowa.model.Product;

public class ProductDetailsMapper {

    public static ProductDetilsDto DaoToDto(Product product){
        ProductDetilsDto detilsDto = new ProductDetilsDto();
        detilsDto.id = product.getId();
        detilsDto.name = product.getName();
        detilsDto.icon = bytesToBase64.byteToBase64(product.getIcon());
        detilsDto.picture = bytesToBase64.byteToBase64(product.getPicture());
        detilsDto.description = product.getDescription();
        detilsDto.price = product.getPrice();
        detilsDto.availability = product.getAvailability();
        return detilsDto;
    }

//    public static Product DtoToDao(ProductDetilsDto detilsDto){
        /*
        * todo
        *  dokonczyc
        * */
//    }
}

