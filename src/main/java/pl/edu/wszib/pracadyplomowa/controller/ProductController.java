package pl.edu.wszib.pracadyplomowa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.pracadyplomowa.service.ProductService;

@Controller
//@RequestMapping("/shop")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getById(id));
        return "product-details";
    }

}
