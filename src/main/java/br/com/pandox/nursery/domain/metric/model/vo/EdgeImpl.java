package br.com.pandox.nursery.domain.metric.model.vo;

import org.springframework.util.Assert;

public class EdgeImpl implements Edge {

    private Integer lowest;
    private Integer highest;

    public EdgeImpl(Integer lowest, Integer highest) {
        Assert.notNull(lowest);
        Assert.notNull(highest);

        this.lowest = lowest;
        this.highest = highest;
    }

    @Override
    public Integer getLowest() {
        return lowest;
    }

    @Override
    public Integer getHighest() {
        return highest;
    }
}
