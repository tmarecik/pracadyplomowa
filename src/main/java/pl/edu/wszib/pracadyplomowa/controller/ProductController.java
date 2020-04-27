package pl.edu.wszib.pracadyplomowa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

//    @GetMapping("/products/{id}")
    @GetMapping("/details/{id}")
//    @PostMapping("/details/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        ProductDetilsDto product = productService.getById(id);
        detailsService.addToDetails(product);
//        detailsService.getById(product.getId());
//        model.addAttribute("product", product);
        model.addAttribute("product", detailsService.getById(product.getId()));
        return "product-details";
    }

    @PostMapping("/details/update/{id}")
    public String modifyDetails(@PathVariable Long id, Model model){
        ProductDetilsDto product =  detailsService.getById(id);
        detailsService.update(product);
//        return "redirect:/product-detail";
        model.addAttribute("product", detailsService.getById(product.getId()));
//        return "redirect:/details/{id}";
//        return String.format("redirect:/details/%d", product.getId());
        return String.format("redirect:/basket/%d", product.getId());
    }


// todo to działa
//    @GetMapping("/products/{id}/{id}")
//    public String addToBasket(@PathVariable Long id, Model model) {
//        ProductDetilsDto product = productService.getById(id);
//        basketService.addToBasket(product);
//        model.addAttribute("products", basketService.getBasketProducts());
//        detailsService.clearDetailsMap();
//        return "basket";
//    }

//    @GetMapping("/products/{id}/{id}")
    @GetMapping("/basket/{id}")
    public String addToBasket(@PathVariable Long id, Model model) {
        ProductDetilsDto product = detailsService.getById(id);
        basketService.addToBasket(product);
        model.addAttribute("products", basketService.getBasketProducts());
//        detailsService.clearDetailsMap();
        return "basket";
    }


//    @PostMapping("/basket")
//    public String addAmount(@PathVariable Long id, Model model) {
//        ProductDetilsDto product = detailsService.getById(id);
//        basketService.addToBasket(product);
//        model.addAttribute("products", basketService.getBasketProducts());
//        detailsService.clearDetailsMap();
//        return "basket";
//    }

    @GetMapping("/basket")
    public String basketProducts(Model model){
        model.addAttribute("products", basketService.getBasketProducts());
        return "basket";
    }
}
