package br.com.pandox.nursery.domain.alert.loader;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.model.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertLoaderImpl implements AlertLoader {

    @Autowired
    private AlertRepository repository;



    @Override
    public List<Alert> loadByMetricId(Long id) {
        return repository.findByMetric_id(id);
    }
}
