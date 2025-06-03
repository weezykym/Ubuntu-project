package com.project.weezdompet.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "VENDORS")
@Data
@ToString
public class VendorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_ID")
    private long vendorId;

    @Column(name = "VENDOR_NAME")
    private String vendorName;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;
}
