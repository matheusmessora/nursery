package br.com.pandox.nursery.integration;

import br.com.pandox.nursery.integration.helpers.DataHelper;
import br.com.pandox.nursery.integration.helpers.ITHelper;
import br.com.pandox.nursery.integration.helpers.MetricHelper;
import br.com.pandox.nursery.integration.helpers.MonitorHelper;
import br.com.pandox.nursery.view.data.DataDTO;
import br.com.pandox.nursery.view.metric.MetricDTO;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataControllerIT extends ITHelper {

    private MonitorDTO createMonitor(MonitorDTO monitorDTO) throws Exception {
        return new MonitorHelper()
                .withBaseURL(getBaseURL())
                .withMonitor(monitorDTO)
                .create();
    }

    private MetricDTO createMetric(MetricDTO metricDTO) throws Exception {
        return new MetricHelper()
                .withBaseURL(getBaseURL())
                .withDTO(metricDTO)
                .create();
    }

    private MetricDTO createData(DataDTO dataDTO) throws Exception {
        return new DataHelper()
                .withBaseURL(getBaseURL())
                .withDTO(dataDTO)
                .create();
    }

    private MetricDTO createData() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setMonitor(monitorDTO);
        metricDTO.setName("MetricA");
        metricDTO.setTime_interval(1);

        metricDTO = createMetric(metricDTO);

        DataDTO dto = new DataDTO();
        dto.setMetric(metricDTO);
        dto.setValue(1);

        MetricDTO data = createData(dto);
        Assert.assertEquals(data.getId().longValue(), 1L);
        Assert.assertEquals(data.getDatas().get(0).getId().longValue(), 1L);
        Assert.assertNotNull(data.getDatas().get(0).getDate_creation());
        return data;
    }

    @Test
    public void should_create() throws Exception {
        createData();
    }




}
