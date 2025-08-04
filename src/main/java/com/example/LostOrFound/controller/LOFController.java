package com.example.LostOrFound.controller;

import com.example.LostOrFound.dataEntity.Product;
import com.example.LostOrFound.dto.ProductRequestDto;
import com.example.LostOrFound.dto.ProductResponseDto;
import com.example.LostOrFound.repo.LostOrFoundRepo;
import com.example.LostOrFound.service.LOFservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LOFController {
    @Autowired
    LostOrFoundRepo lostOrFoundRepo;
    @Autowired
    LOFservice loFservice;

    public LOFController(LOFservice loFservice) {
        this.loFservice = loFservice;
    }

    @GetMapping("/all")
    public List<Product> getALl() {
        return loFservice.getEmAll();
    }

    @GetMapping("/id/{id}")
    public Product getByTheirId(@PathVariable Long id) {
        return loFservice.getProductById(id);
    }

    @PostMapping("/add")
    public ProductResponseDto addProduct(@Valid @RequestBody ProductRequestDto product) {
        return loFservice.addLOFPro(product);
    }

    @PutMapping("/update")
    public Product updatePro(@RequestBody Product product) {
        return loFservice.updateProduct(product);
    }

    @GetMapping("/filter")
    public List<Product> findAllByLocation(@RequestParam(name = "location", required = false) String location,
                                           @RequestParam(name = "name", required = false) String productName,
                                           @RequestParam(name = "status", required = false) String status) {
        List<Product> products = loFservice.getEmAll();
        if (location != null) {
            return loFservice.getByLocation(location);
        }
        if (productName != null) {
            return loFservice.getByProductName(productName);
        }
        if (status != null) {
            return loFservice.getByStatus(status);
        }
        return products;
    }


    @DeleteMapping("/delete/{id}")
    public String removeById(@PathVariable long id) {
        loFservice.deleteProductById(id);
        return "Item Removed successfully" + id;
    }

    @GetMapping("/count/lost/{location}")
    public int getCountByLocation(@PathVariable String location) {
        List<Product> products = lostOrFoundRepo.findAll();
        return loFservice.countLostProductsInLocation(products,
                location);
    }

    @GetMapping("/seven")
    public List<Product> getSevenDaysData() {
        List<Product> allProducts = lostOrFoundRepo.findAll();
        return loFservice.getBy7days(allProducts);
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
        return loFservice.getByPlace(allPods, location);
    }

}
