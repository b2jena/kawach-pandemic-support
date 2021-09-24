package com.stackroute.resource.Service;
import com.stackroute.resource.Model.Resources;

import java.util.List;
public interface ResourceService {
    Resources SaveResource(Resources resources);

    List<Resources> getAllResources();

    Resources UpdateResource(Resources resources);
}
