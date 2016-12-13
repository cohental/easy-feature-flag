package com.demo.featureflags.integrationtests;


import java.util.Collection;
import java.util.List;

public interface FeatureFlagsSampleService {

    Integer sum(Integer a, Integer b);

    void updateMember(Integer count);

    Integer getMember();

    List<String> unionLists(List<String> listA, List<String> listB);

    Integer returnInt(Integer inputParam);

    Long returnLong(Long inputParam);

    Double returnDouble(Double inputParam);

    Boolean returnTrue(Boolean inputParam);

    Boolean returnFalse(Boolean inputParam);

    List<String> returnList(List<String> inputParam);

    Collection<String> returnCollection(Collection<String> inputParam);

    SampleClass returnSampleClass(SampleClass inputParam);

    Integer[] returnIntegerArray(Integer[] inputParam);

    SampleClass[] returnSampleClassArray(SampleClass[] inputParam);

    Integer returnInputParamInteger(Integer paramA, Integer paramB, Long paramC);
}
