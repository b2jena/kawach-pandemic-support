package com.stackroute.resource.service;
import com.stackroute.resource.exception.ResourceAlreadyExistsException;
import com.stackroute.resource.model.Resources;

import java.util.List;

public interface ResourceService {
    Resources saveResource(Resources resources) throws ResourceAlreadyExistsException;

    List<Resources> getAllResources();

    Resources updateResource(Resources resources);
}
