package com.project.weezdompet.web.rest;

import com.project.weezdompet.services.VendorService;
import com.project.weezdompet.web.errors.BadRequestException;
import com.project.weezdompet.web.models.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VendorRestController {
    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/vendors")
    public List<Vendor> getVendors() {
        return this.vendorService.getAllVendors();
    }

    @GetMapping("/vendor/{id}")
    public Vendor getVendor(@PathVariable("id") long id) {
        return this.vendorService.getVendor(id);
    }

    @PostMapping("/createVendor{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return this.vendorService.createOrUpdateVendor(vendor);
    }

    @PutMapping("/updateVendor/{id}")
    public Vendor updateVendor(@PathVariable("id") long id, @RequestBody Vendor vendor) {
        if (id != vendor.getVendorId() ) {
            throw new BadRequestException("Vendor id mismatch");
        }
        return this.vendorService.createOrUpdateVendor(vendor);
    }

    @DeleteMapping("/deleteVendor{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteVendor(@PathVariable("id") long id) {
        this.vendorService.deleteVendor(id);
    }
}
