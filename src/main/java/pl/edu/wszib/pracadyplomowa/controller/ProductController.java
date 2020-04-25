package pl.edu.wszib.pracadyplomowa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.service.BasketService;
import pl.edu.wszib.pracadyplomowa.service.DetailsService;
import pl.edu.wszib.pracadyplomowa.service.ProductService;

@Controller
//@RequestMapping("/shop")
public class ProductController {

    ProductService productService;
    DetailsService detailsService;
    BasketService basketService;

    public ProductController(ProductService productService, DetailsService detailsService, BasketService basketService) {
        this.productService = productService;
        this.detailsService = detailsService;
        this.basketService = basketService;
    }

    @GetMapping
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/products")
    public String products(Model model){
        detailsService.clearDetailsMap();
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

//    todo zweryfikowac bonie wiadomo czy działa
    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        ProductDetilsDto product = productService.getById(id);
        detailsService.addToDetails(product);
        model.addAttribute("product", product);
        return "product-details";
    }

//    todo skonczyc bo nie działa
    @GetMapping("/products/{id}/{id}")
    public String addToBasket(@PathVariable Long id, Model model) {
        ProductDetilsDto product = detailsService.getById(id);
        basketService.addToBasket(product);
        model.addAttribute("products", basketService.getBasketProducts());
        detailsService.clearDetailsMap();
        return "basket";
    }
// todo nie działa
    @GetMapping("/basket")
    public String basketProducts(Model model){
        model.addAttribute("products", basketService.getBasketProducts());
        return "basket";
    }
}
