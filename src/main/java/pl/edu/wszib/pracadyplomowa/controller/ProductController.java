package pl.edu.wszib.pracadyplomowa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.pracadyplomowa.dto.BasketProduct;
import pl.edu.wszib.pracadyplomowa.dto.ProductDetilsDto;
import pl.edu.wszib.pracadyplomowa.model.Product;
import pl.edu.wszib.pracadyplomowa.service.BasketService;
import pl.edu.wszib.pracadyplomowa.service.DetailsService;
import pl.edu.wszib.pracadyplomowa.service.ProductService;


@Controller
//@RequestMapping("/shop")
public class ProductController {

    ProductService productService;
    DetailsService detailsService;
    BasketService basketService;

    public ProductController(ProductService productService,
                             DetailsService detailsService,
                             BasketService basketService) {
        this.productService = productService;
        this.detailsService = detailsService;
        this.basketService = basketService;
    }

    @GetMapping
    public String welcome(){
        return "welcome";
    }

    @GetMapping("shipping")
    public String shipping(Model model){      //umożliwia przekazywanie danych do nowego szablonu
        model.addAttribute("shippingMethodName1", "Paczkomat");             //dwefiniuje pola i wartości w szablonie shipping!!!! do których bedziemy sie odwoływać
        model.addAttribute("shippingMethodName2", "Kurier -przedpłata");
        model.addAttribute("shippingMethodName3", "Kurier -pobranie");
        model.addAttribute("shippingMethodPrice1", 10.50);
        model.addAttribute("shippingMethodPrice2", 15.00);
        model.addAttribute("shippingMethodPrice3", 20.20);
        return "shipping";
    }

    @GetMapping("/products")
    public String products(Model model){
        detailsService.clearDetailsMap();
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping("/details/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        ProductDetilsDto product = productService.getById(id);
        detailsService.addToDetails(product);
        model.addAttribute("product", detailsService.getById(product.getId()));
        return "product-details";
    }

    @PostMapping("/details/update")
    public String fromDetailsAddToBasket(ProductDetilsDto product){
        BasketProduct basketProduct = basketService.detailsToBasket(product);
        basketService.addToBasket(basketProduct);
        /*addidtion Map<Product> was created it will be use for DB modify after BUY button press*/
        Product productDao = productService.getProductDaoById(product.getId());
        basketService.addToProductMap(productDao);
        return "redirect:/basket";
    }

    @GetMapping("/basket")
    public String basketProducts(Model model){
        double basketTotalPrice = basketService.basketTotalPrice();
        int basketTotalItems = basketService.basketTotalItems();
        model.addAttribute("products", basketService.getBasketProducts());
        model.addAttribute("basketTotalPrice", basketTotalPrice);
        model.addAttribute("basketTotalItems", basketTotalItems);
        return "basket";
    }

    @GetMapping("/basket/delete/{id}")
    public String deleteFromBasket(@PathVariable Long id){
        basketService.delete(id);
        return "redirect:/basket";
    }

    @GetMapping("/basket/deleteAll")
    public String deleteAllFromBasket(){
        basketService.deleteAll();
        return "redirect:/basket";
    }

    @GetMapping("/basket/buy")
    public String buy(){
        basketService.buyAllStuff();
        basketService.deleteAll();
        detailsService.clearDetailsMap();
        return "redirect:/products";
    }

    @GetMapping("/basket/{id}")
    public String getBasketProduct(@PathVariable Long id, Model model) {
        BasketProduct basketProduct = basketService.getBasketProductById(id);
        Product dBproduct = basketService.getProductFromProductMapById(id);
        model.addAttribute("basketProduct", basketProduct);
        model.addAttribute("dBproduct", dBproduct);
        return "basketProduct";
    }


}
