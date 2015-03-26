package br.com.pandox.nursery.domain.metric.command;

import br.com.pandox.nursery.domain.metric.command.handler.AddDataToMetricCommandHandler;
import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import br.com.pandox.nursery.view.data.DataDTO;

public class AddDataToMetricCommand implements Command {

    private Long metricId;
    private DataDTO dataDTO;

    public AddDataToMetricCommand(Long metricId, DataDTO dataDTO) {
        this.metricId = metricId;
        this.dataDTO = dataDTO;
    }

    @Override
    public Class<? extends CommandHandler> getExecutorType() {
        return AddDataToMetricCommandHandler.class;
    }

    public Long getMetricId() {
        return metricId;
    }

    public DataDTO getDataDTO() {
        return dataDTO;
    }
}
