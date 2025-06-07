package com.project.weezdompet.web.rest;

import com.project.weezdompet.services.ServiceService;
import com.project.weezdompet.web.errors.BadRequestException;
import com.project.weezdompet.web.models.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceRestController {
    
    private final ServiceService serviceService;

    public ServiceRestController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    public List<Service> getServices() {
        return this.serviceService.getAllServices();
    }

    @GetMapping("/service/{id}")
    public Service getService(@PathVariable("id") long id) {
        return this.serviceService.getService(id);
    }

    @PostMapping("/createService/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Service createService(@RequestBody Service service) {
        return this.serviceService.createOrUpdateService(service);
    }

    @PutMapping("/updateService{id}")
    public Service updateService(@PathVariable("id") long id, @RequestBody Service service) {
        if (id != service.getServiceId() ) {
            throw new BadRequestException("Service id mismatch");
        }
        return this.serviceService.createOrUpdateService(service);
    }

    @DeleteMapping("/deleteService/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteService(@PathVariable("id") long id) {
        this.serviceService.deleteService(id);
    }
}
