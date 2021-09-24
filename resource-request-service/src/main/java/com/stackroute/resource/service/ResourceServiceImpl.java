package com.stackroute.resource.Service;

import com.stackroute.resource.Model.Resources;
import com.stackroute.resource.Repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService{
    private ResourceRepository resourceRepository;
    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
    @Override
    public Resources SaveResource(Resources resources) {
        return resourceRepository.save(resources);
    }
    @Override
    public List<Resources> getAllResources() {
        return (List<Resources>) resourceRepository.findAll();
    }
    @Override
    public  Resources UpdateResource(Resources resources){
        return resourceRepository.save(resources);
    }
}
