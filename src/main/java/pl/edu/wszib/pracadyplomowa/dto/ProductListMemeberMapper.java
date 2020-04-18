package pl.edu.wszib.pracadyplomowa.dto;


import pl.edu.wszib.pracadyplomowa.model.Product;

public class ProductListMemeberMapper {

    public static ProductListMemberDto DaoToDto(Product product){
        ProductListMemberDto listMember = new ProductListMemberDto();
        listMember.id = product.getId();
        listMember.name = product.getName();
        listMember.icon = bytesToBase64.byteToBase64(product.getIcon());
        listMember.price = product.getPrice();
        return listMember;
    }

}
