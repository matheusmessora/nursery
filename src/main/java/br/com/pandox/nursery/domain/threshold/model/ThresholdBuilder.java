package br.com.pandox.nursery.domain.threshold.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class ThresholdBuilder {

    private Integer value;
    private Threshold.ThresholdType type;

    public ThresholdBuilder type(String type){
        if(StringUtils.isEmpty(type)) {
            illegalType();
        }

        Threshold.ThresholdType thresholdType;
        try {
            thresholdType = Threshold.ThresholdType.valueOf(type);
            this.type = thresholdType;
        } catch (IllegalArgumentException e) {
            illegalType();
        }

        return this;
    }

    public ThresholdBuilder value(Integer value) {
        this.value = value;
        return this;
    }

    public Threshold build(){
        Assert.notNull(value, "value must not be null");
        Assert.notNull(type, "type must not be null");

        return new ThresholdEntity(value, type);
    }

    private void illegalType(){
        throw new IllegalArgumentException("Malformed attribute type. It should be one of the following " + Arrays.asList(Threshold.ThresholdType.values()));
    }
}
