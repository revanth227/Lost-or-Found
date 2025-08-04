package com.example.LostOrFound.dto;

import com.example.LostOrFound.dataEntity.ProductType;

public class ProductRequestDto {
    private String productName;
    private String location;
    private String status;
    private String description;
    private String reportByContact;
    private String resolvedByContact;

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
}
