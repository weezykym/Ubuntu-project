package com.project.weezdompet.services;

import com.project.weezdompet.data.entities.VendorEntity;
import com.project.weezdompet.data.repositories.VendorRepository;
import com.project.weezdompet.web.errors.NotFoundException;
import com.project.weezdompet.web.models.Vendor;
import com.project.weezdompet.web.models.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    
    private final VendorRepository vendorRepository;
    
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendors() {
        Iterable<VendorEntity> vendorEntities = this.vendorRepository.findAll();
        List<Vendor> vendors = new ArrayList<>();
        vendorEntities.forEach(vendorEntity -> {
            vendors.add(translateDbToWeb(vendorEntity));
        });
        return vendors;
    }

    public Vendor getVendor(long id) {
        Optional<VendorEntity> optional = this.vendorRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Vendor with id " + id + " not found");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Vendor createOrUpdateVendor(Vendor vendor){
        VendorEntity entity = this.translateWebToDb(vendor);
        entity = this.vendorRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteVendor(long id) {
        this.vendorRepository.deleteById(id);
    }


    private VendorEntity translateWebToDb(Vendor vendor) {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setVendorId(vendor.getVendorId());
        vendorEntity.setVendorName(vendor.getName());
        vendorEntity.setContact(vendor.getContact());
        vendorEntity.setPhone(vendor.getPhoneNumber());
        vendorEntity.setEmail(vendor.getEmailAddress());
        vendorEntity.setAddress(vendor.getAddress());

        return vendorEntity;
    }

    private Vendor translateDbToWeb(VendorEntity vendorEntity) {
        return new Vendor(vendorEntity.getVendorId(),
                vendorEntity.getVendorName(),
                vendorEntity.getContact(),
                vendorEntity.getEmail(),
                vendorEntity.getPhone(),
                vendorEntity.getAddress());
    }
}
