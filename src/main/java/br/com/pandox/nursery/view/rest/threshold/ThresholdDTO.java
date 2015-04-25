package br.com.pandox.nursery.view.rest.threshold;

import br.com.pandox.nursery.domain.threshold.model.Threshold;
import br.com.pandox.nursery.view.rest.AbstractDTO;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ThresholdDTO extends AbstractDTO {


    private Integer value;

    private String type;

    private MetricDTO metricDTO;

    public ThresholdDTO() {
    }

    public ThresholdDTO(Threshold threshold) {
        this.value = threshold.getValue();
        this.type = threshold.fromType().name();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MetricDTO getMetricDTO() {
        return metricDTO;
    }

    public void setMetricDTO(MetricDTO metricDTO) {
        this.metricDTO = metricDTO;
    }
}
