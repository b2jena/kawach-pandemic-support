package com.stackroute.resource.service;
import com.stackroute.resource.model.Resources;

import java.util.List;

public interface ResourceService {
    Resources SaveResource(Resources resources);

    List<Resources> getAllResources();

    Resources UpdateResource(Resources resources);
}
