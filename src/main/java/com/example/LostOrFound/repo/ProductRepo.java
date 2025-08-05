package com.example.LostOrFound.repo;

import com.example.LostOrFound.dataEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByproductName(String productName);

    default List<Product> findByProductName() {
        return findByProductName(null);
    }

    List<Product> findByProductName(String productName);

    List<Product> findBylocation(String location);

    List<Product> findByStatus(String status);

    List<Product> findByreportByContact(String reportByContact);

    List<Product> findBydateTime(LocalDate date);
}


