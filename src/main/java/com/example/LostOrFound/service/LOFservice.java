package com.example.LostOrFound.service;

import com.example.LostOrFound.dataEntity.Product;
import com.example.LostOrFound.dto.ProductRequestDto;
import com.example.LostOrFound.dto.ProductResponseDto;
import com.example.LostOrFound.exception.ProductNotFoundException;
import com.example.LostOrFound.repo.LostOrFoundRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LOFservice {
    @Autowired
    LostOrFoundRepo lostOrFoundRepo;

    LOFservice(LostOrFoundRepo lostOrFoundRepo) {
        this.lostOrFoundRepo = lostOrFoundRepo;
    }

    public List<Product> getEmAll() {
        return lostOrFoundRepo.findAll();
    }

    @Transactional
    public Product getProductById(Long id) {
        return lostOrFoundRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }


    @Transactional
    public ProductResponseDto addLOFPro(@Valid ProductRequestDto requestDto) {
        if (requestDto.getProductName() == null) {
            throw new RuntimeException("Can't allow without any name");
        }
        Product product1 = new Product();
        product1.setProductName(requestDto.getProductName());
        product1.setDescription(requestDto.getDescription());
        product1.setLocation(requestDto.getLocation());
        product1.setStatus(requestDto.getStatus());
        product1.setReportByContact(requestDto.getReportByContact());
        product1.setResolvedByContact(requestDto.getResolvedByContact());
        lostOrFoundRepo.save(product1);


        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductName(product1.getProductName());
        productResponseDto.setLocation(product1.getLocation());
        productResponseDto.setStatus(product1.getStatus());
        productResponseDto.setId(product1.getId());
        productResponseDto.setDateTime(product1.getDateTime());
        productResponseDto.setReportByContact(product1.getReportByContact());
        productResponseDto.setResolvedByContact(product1.getResolvedByContact());
        productResponseDto.setDescription(product1.getDescription());


        return productResponseDto;
    }


    @Transactional
    public Product updateProduct(@RequestBody Product product) {
        Optional<Product> product1 = lostOrFoundRepo.findById(product.getId());
        if (product1.isEmpty()) {
            throw new RuntimeException("No Product found to update")
                    ;
        } else {
            return lostOrFoundRepo.save(product);
        }
    }


    public void deleteProductById(long id) throws RuntimeException {
        Optional<Product> productOptional = lostOrFoundRepo.findById(id);
        if (productOptional.isPresent()) {
            lostOrFoundRepo.deleteById(id);
        } else {
            throw new RuntimeException("No product found with the id " + id);
        }
    }


    public long countProduct() {

        return lostOrFoundRepo.count();
    }

    public List<Product> getByReporterContact(String reportByContact) {

        return lostOrFoundRepo.findByreportByContact(reportByContact);
    }

    public int countLostProductsInLocation(List<Product> allProducts, String targetLocation) {
        int count = 0;
        if (allProducts == null) {
            return 0;
        }
        String lowerCaseTargetLocation = targetLocation.toLowerCase();
        for (Product product : allProducts) {
            if (product.getStatus() != null && product.getStatus().equalsIgnoreCase("lost")) {
                if (product.getLocation() != null && product.getLocation().toLowerCase().equals(lowerCaseTargetLocation)) {
                    count++;
                }
            }
        }

        return count;
    }

    public List<Product> getBy7days(List<Product> allProducts) {
        if (allProducts == null) {
            return null;
        }

        return allProducts.stream()
                .filter(product -> product.getStatus() != null && product.getStatus().equalsIgnoreCase("lost"))
                .filter(product -> product.getDateTime().isAfter(LocalDateTime.now().minusDays(7)))
                .toList();
    }

    public List<Product> getByPlace(List<Product> allProds, String targetPlace) {
        List<Product> products;
        products = allProds.stream().filter(pro -> pro.getLocation().toLowerCase().equals(targetPlace)).collect(Collectors.toList());
        return products;
    }


    public List<Product> filterProduct(String location, String productName, String status, LocalDateTime date) {
        List<Product> products = lostOrFoundRepo.findAll();
        if (location != null) {
            return lostOrFoundRepo.findBylocation(location);
        }
        if (productName != null) {
            products = products.stream().filter(p -> p.getProductName().equals(productName)).toList();
        }
        if (status != null) {
            products = products.stream().filter(s -> s.getStatus().equals(status)).toList();
        }
        if (date != null) {
            products = products.stream().filter(d -> d.getDateTime().equals(date)).toList();

        }
        return products;
    }
}
