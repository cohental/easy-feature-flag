package com.demo.featureflags.api.services;


import com.demo.featureflags.api.enums.Feature;

public interface FeatureFlagsService {

    Boolean isActive(Feature feature);

    void activateFeature(Feature feature);

    void deactivateFeature(Feature feature);

}
