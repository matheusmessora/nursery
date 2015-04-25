package br.com.pandox.nursery.domain.threshold.model;

import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
public class ThresholdEntity implements Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer value;

    @Column
    private ThresholdType type;

    public ThresholdEntity() {
    }



    public ThresholdEntity(Integer value, ThresholdType type) {
        Assert.notNull(value);
        Assert.notNull(type);

        this.value = value;
        this.type = type;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public ThresholdType fromType() {
        return type;
    }

}
