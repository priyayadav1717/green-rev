package com.greenrev.greenrev.controller;

import com.greenrev.greenrev.entity.Vendor;
import com.greenrev.greenrev.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins = "*")
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    // Register/Add new verified shopkeeper
    @PostMapping("/register")
    public ResponseEntity<Vendor> registerVendor(@RequestBody Vendor vendor) {
        // Ensure default values if null to prevent PropertyValueException
        if (vendor.getIsVerified() == null) {
            vendor.setIsVerified(true);
        }

        Vendor savedVendor = vendorRepository.save(vendor);
        return ResponseEntity.ok(savedVendor);
    }

    // Verify vendor by seller ID
    @GetMapping("/{sellerId}")
    public ResponseEntity<Vendor> getVendorDetails(@PathVariable String sellerId) {
        return vendorRepository.findBySellerId(sellerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all registered vendors
    @GetMapping("/all")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorRepository.findAll());
    }
}