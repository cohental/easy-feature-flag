package com.demo.featureflags.integrationtests;


import com.demo.featureflags.api.annotations.ReturnParameter;
import com.demo.featureflags.api.enums.ReturnValue;
import com.google.common.collect.Lists;
import com.demo.featureflags.api.annotations.FeatureFlag;
import com.demo.featureflags.api.enums.Feature;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class FeatureFlagsSampleServiceImpl implements FeatureFlagsSampleService {

    private Integer member = 0;

    public Integer getMember() {
        return member;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE)
    public Integer sum(Integer a, Integer b) {
        return (a == null ? 0 : a) + (b == null ? 0 : b);
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE)
    public void updateMember(Integer count) {
        member = count;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE)
    public List<String> unionLists(List<String> listA, List<String> listB) {
        List<String> result = Lists.newArrayList();
        result.addAll(listA);
        result.addAll(listB);
        return result;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.Zero)
    public Integer returnInt(Integer inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.Zero)
    public Long returnLong(Long inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.Zero)
    public Double returnDouble(Double inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.False)
    public Boolean returnTrue(Boolean inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.True)
    public Boolean returnFalse(Boolean inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.EmptyCollection)
    public List<String> returnList(List<String> inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.EmptyCollection)
    public Collection<String> returnCollection(Collection<String> inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.NewInstance)
    public SampleClass returnSampleClass(SampleClass sampleClass) {
        return sampleClass;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.EmptyCollection)
    public Integer[] returnIntegerArray(Integer[] inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.EmptyArray)
    public SampleClass[] returnSampleClassArray(SampleClass[] inputParam) {
        return inputParam;
    }

    @FeatureFlag(feature = Feature.SAMPLE_FEATURE, whenInactive = ReturnValue.MethodParameter)
    public Integer returnInputParamInteger(Integer paramA, @ReturnParameter Integer paramB, Long paramC) {
        return paramA;
    }
}
