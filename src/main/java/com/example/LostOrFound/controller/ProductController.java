package com.example.LostOrFound.controller;

import com.example.LostOrFound.dataEntity.Product;
import com.example.LostOrFound.dto.ProductRequestDto;
import com.example.LostOrFound.dto.ProductResponseDto;
import com.example.LostOrFound.repo.LostOrFoundRepo;
import com.example.LostOrFound.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    LostOrFoundRepo lostOrFoundRepo;
    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getALl() {
        return productService.getEmAll();
    }

    @GetMapping("/id/{id}")
    public Product getByTheirId(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public ProductResponseDto addProduct(@Valid @RequestBody ProductRequestDto product) {
        return productService.addLOFPro(product);
    }

    @PutMapping("/update")
    public Product updatePro(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/filter")
    public List<Product> findAllByLocation(@RequestParam(name = "location", required = false) String location,
                                           @RequestParam(name = "name", required = false) String productName,
                                           @RequestParam(name = "status", required = false) String status,
                                           @RequestParam(name = "date", required = false) LocalDateTime date
    ) {

        return productService.filterProduct(location, productName, status, date);
    }


    @DeleteMapping("/delete/{id}")
    public String removeById(@PathVariable long id) {
        productService.deleteProductById(id);
        return "Item Removed successfully" + id;
    }

    @GetMapping("/count/lost/{location}")
    public int getCountByLocation(@PathVariable String location) {
        List<Product> products = lostOrFoundRepo.findAll();
        return productService.countLostProductsInLocation(products,
                location);
    }

    @GetMapping("/seven")
    public List<Product> getSevenDaysData() {
        List<Product> allProducts = lostOrFoundRepo.findAll();
        return productService.getBy7days(allProducts);
    }

    @GetMapping("/ses")
    public String getSeess(HttpServletRequest request) {
        return "This is the Session id " + request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrf(HttpServletRequest servletRequest) {
        return (CsrfToken) servletRequest.getAttribute("_csrf");

    }

    public List<Product> stringLoaction(String location) {
        List<Product> allPods = lostOrFoundRepo.findAll();
        return productService.getByPlace(allPods, location);
    }

}
