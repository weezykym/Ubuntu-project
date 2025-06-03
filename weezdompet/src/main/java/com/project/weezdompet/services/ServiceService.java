package com.project.weezdompet.services;

import com.project.weezdompet.data.entities.ProductEntity;
import com.project.weezdompet.data.entities.ServiceEntity;
import com.project.weezdompet.data.repositories.ServiceRepository;
import com.project.weezdompet.web.errors.NotFoundException;
import com.project.weezdompet.web.models.Product;
import com.project.weezdompet.web.models.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository){
        this.serviceRepository = serviceRepository;
    }

    /*This method returns a list of Services.
    If you give it an email (filterEmail), it returns only that Service.
    If you don't give an email, it returns all Services.
    It takes data from the database (ServiceEntity) and converts it into a format used by the web (Service).
    */
    public List<Service> getAllServices() {
        Iterable<ServiceEntity> serviceEntities = this.serviceRepository.findAll();
        List<Service> services = new ArrayList<>();
        serviceEntities.forEach(serviceEntity -> {
            services.add(translateDbToWeb(serviceEntity));
        });
        return services;
    }

    public Service getService(long id) {
        Optional<ServiceEntity> optional = this.serviceRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Service with id " + id + " not found");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Service createOrUpdateService(Service Service){
        ServiceEntity entity = this.translateWebToDb(Service);
        entity = this.serviceRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteService(long id) {
        this.serviceRepository.deleteById(id);
    }


    private ServiceEntity translateWebToDb(Service service) {
        ServiceEntity entity = new ServiceEntity();
        entity.setServiceId(service.getServiceId());
        entity.setName(service.getName());
        entity.setPrice(service.getPrice());

        return entity;
    }

    private Service translateDbToWeb(ServiceEntity entity) {
        return new Service(entity.getServiceId(), entity.getName(), entity.getPrice());
    }    
}
