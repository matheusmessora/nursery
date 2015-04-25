package br.com.pandox.nursery.domain.threshold.model;

public interface Threshold {

    enum ThresholdType {
        ABOVE, BELOW
    }

    Integer getValue();

    ThresholdType fromType();
}
