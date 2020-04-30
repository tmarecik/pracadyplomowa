package pl.edu.wszib.pracadyplomowa.dto;


public class ProductBasketMapper {

    public BasketProduct detailsToBasket(ProductDetilsDto product){
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setId(product.getId());
        basketProduct.setName(product.getName());
        basketProduct.setIcon(product.getIcon());
        basketProduct.setPrice(product.getPrice());
        basketProduct.setAmount(product.getAmount());
        basketProduct.setTotalPrice();
        return basketProduct;
    }

}
