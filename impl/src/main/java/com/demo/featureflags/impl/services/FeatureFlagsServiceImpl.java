package com.demo.featureflags.impl.services;


import com.demo.featureflags.api.enums.Feature;
import com.demo.featureflags.api.services.FeatureFlagsService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class FeatureFlagsServiceImpl implements FeatureFlagsService {

    private Map<Feature, Boolean> featureMap = Maps.newHashMap();

    @PostConstruct
    private void init() {
        featureMap.clear();
    }

    public Boolean isActive(Feature feature) {
        return featureMap.get(feature);
    }

    public void activateFeature(Feature feature) {
        updateFeatureFlags(feature, true);
    }

    public void deactivateFeature(Feature feature) {
        updateFeatureFlags(feature, false);
    }

    private void updateFeatureFlags(Feature feature, boolean status) {
        featureMap.put(feature, status);
    }
}
