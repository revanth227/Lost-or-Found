package com.example.LostOrFound.dataEntity;


import com.example.LostOrFound.dto.ProductResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "You must Mention Name")
    @NotBlank
    @Size(min = 2, max = 150, message = "Name characters must in between 2 to 150 ")
    private String productName;
    private String location;
    @CreationTimestamp
    private LocalDateTime dateTime;


    @Column(name = "status")
    private String status;
    private String description;
    private String reportByContact;
    private String resolvedByContact;


    public Product() {
    }

    public String getReportByContact() {
        return reportByContact;
    }

    public void setReportByContact(String reportByContact) {
        this.reportByContact = reportByContact;
    }

    public String getResolvedByContact() {
        return resolvedByContact;
    }

    public void setResolvedByContact(String resolvedByContact) {
        this.resolvedByContact = resolvedByContact;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
