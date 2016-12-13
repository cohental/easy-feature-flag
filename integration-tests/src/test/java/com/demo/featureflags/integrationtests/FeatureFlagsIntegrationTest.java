package com.demo.featureflags.integrationtests;


import com.google.common.collect.Lists;
import com.demo.featureflags.api.enums.Feature;
import com.demo.featureflags.api.services.FeatureFlagsService;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:META-INF/spring/test/feature-flags-test-context.xml",
        "classpath*:META-INF/spring/feature-flags-context.xml"
})
public class FeatureFlagsIntegrationTest  {

    @Autowired
    private FeatureFlagsService featureFlagsService;

    @Autowired
    private FeatureFlagsSampleService featureFlagsSampleService;

    @Test
    public void testMethodWithReturnIntegerNull() {

        Integer a = 1;
        Integer b = 9;

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Integer result = featureFlagsSampleService.sum(a, b);
        assertThat(result).isEqualTo(10);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        result = featureFlagsSampleService.sum(a, b);
        assertNull(result);
    }


    @Test
    public void testMethodWithReturnVoidNull() {

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Integer memberBefore = featureFlagsSampleService.getMember();
        featureFlagsSampleService.updateMember(20);
        Integer memberAfter = featureFlagsSampleService.getMember();
        assertThat(memberAfter).isNotEqualTo(memberBefore);
        assertThat(memberAfter).isEqualTo(20);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        memberBefore = featureFlagsSampleService.getMember();
        featureFlagsSampleService.updateMember(70);
        memberAfter = featureFlagsSampleService.getMember();
        assertThat(memberAfter).isEqualTo(memberBefore);

    }


    @Test
    public void testMethodWithReturnListNull() {
        List<String> listA = Lists.newArrayList("listA");
        List<String> listB = Lists.newArrayList("listB");

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        List<String> resultList = featureFlagsSampleService.unionLists(listA, listB);
        assertThat(resultList).isNotNull();
        assertThat(resultList).hasSize(2);
        assertThat(resultList).contains("listA", "listB");

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultList = featureFlagsSampleService.unionLists(listA, listB);
        assertThat(resultList).isNull();

    }

    @Test
    public void testReturnValueIntegerZero() {
        Integer inputParam = 10;
        Integer intDefault = 0;

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Integer resultValue = featureFlagsSampleService.returnInt(inputParam);
        assertThat(resultValue).isEqualTo(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnInt(inputParam);
        assertThat(resultValue).isEqualTo(intDefault);
    }

    @Test
    public void testReturnValueLongZero() {

        Long inputParam = 10L;
        Long longDefault = 0L;

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Long resultValue = featureFlagsSampleService.returnLong(inputParam);
        assertThat(resultValue).isEqualTo(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnLong(inputParam);
        assertThat(resultValue).isEqualTo(longDefault);
    }

    @Test
    public void testReturnValueDoubleZero() {

        Double inputParam = 10.5D;
        Double doubleDefault = 0.0D;

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Double resultValue = featureFlagsSampleService.returnDouble(inputParam);
        assertThat(resultValue).isEqualTo(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnDouble(inputParam);
        assertThat(resultValue).isEqualTo(doubleDefault);
    }

    @Test
    public void testReturnValueListWithEmptyCollection() {

        List<String> inputParam = Lists.newArrayList("A", "B", "C");

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        List<String> resultValue = featureFlagsSampleService.returnList(inputParam);
        assertThat(resultValue).isSameAs(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnList(inputParam);
        assertThat(resultValue).isNotNull();
        assertThat(resultValue).isEmpty();
    }

    @Test
    public void testReturnValueCollectionWithEmptyCollection() {

        Collection<String> inputParam = Lists.newArrayList("A", "B", "C");

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Collection<String> resultValue = featureFlagsSampleService.returnCollection(inputParam);
        assertThat(resultValue).isSameAs(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnCollection(inputParam);
        assertThat(resultValue).isNotNull();
        assertThat(resultValue).isEmpty();
    }

    @Test
    public void testReturnValueSampleClassWithEmptyInstance() {

        SampleClass inputParam = new SampleClass();
        inputParam.setA(2);
        inputParam.setB(3);

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        SampleClass resultValue = featureFlagsSampleService.returnSampleClass(inputParam);
        assertThat(resultValue).isSameAs(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnSampleClass(inputParam);
        assertThat(resultValue).isNotNull();
        assertThat(resultValue.getA()).isEqualTo(0);
        assertThat(resultValue.getB()).isEqualTo(0);
    }

    @Test
    public void testReturnValueArrayWithEmptyArray() {

        Integer[]  inputParam = {1,2};

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Integer[] resultValue = featureFlagsSampleService.returnIntegerArray(inputParam);
        assertThat(resultValue).isSameAs(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnIntegerArray(inputParam);
        assertThat(resultValue).isNotNull();
        assertThat(resultValue).hasSize(0);

    }

    @Test
    public void testReturnSampleClassArrayWithEmptyArray() {

        SampleClass[]  inputParam = {new SampleClass(),new SampleClass()};

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        SampleClass[] resultValue = featureFlagsSampleService.returnSampleClassArray(inputParam);
        Assertions.assertThat(resultValue).isSameAs(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnSampleClassArray(inputParam);
        Assertions.assertThat(resultValue).isNotNull();
        Assertions.assertThat(resultValue).hasSize(0);

    }


    @Test
    public void testReturnValueIntegerWithMethodParameter() {
        Integer paramA = 5;
        Integer paramB = 10;
        Long paramC = 20L;

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Integer resultValue = featureFlagsSampleService.returnInputParamInteger(paramA, paramB, paramC);
        assertThat(resultValue).isEqualTo(paramA);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnInputParamInteger(paramA, paramB, paramC);
        assertThat(resultValue).isEqualTo(paramB);

    }

    @Test
    public void testReturnValueBooleanWithTrue() {
        Boolean inputParam = true;

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Boolean resultValue = featureFlagsSampleService.returnTrue(inputParam);
        assertThat(resultValue).isEqualTo(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnTrue(inputParam);
        assertThat(resultValue).isEqualTo(false);

    }

    @Test
    public void testReturnValueBooleanWithFalse() {
        Boolean inputParam = false;

        featureFlagsService.activateFeature(Feature.SAMPLE_FEATURE);
        Boolean resultValue = featureFlagsSampleService.returnFalse(inputParam);
        assertThat(resultValue).isEqualTo(inputParam);

        featureFlagsService.deactivateFeature(Feature.SAMPLE_FEATURE);
        resultValue = featureFlagsSampleService.returnFalse(inputParam);
        assertThat(resultValue).isEqualTo(true);

    }
}
