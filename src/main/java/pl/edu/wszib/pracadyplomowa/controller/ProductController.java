package pl.edu.wszib.pracadyplomowa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.pracadyplomowa.service.ProductService;

@Controller
@RequestMapping("/shop")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productService.getProducts());
        return "products";
    }


}
