package com.example.LostOrFound.dto;

import com.example.LostOrFound.dataEntity.ProductType;

import java.time.LocalDateTime;

public class ProductResponseDto {
    private Long id;
    private String productName;
    private String location;
    private String status;
    private String description;
    private String reportByContact;
    private String resolvedByContact;
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }




}

