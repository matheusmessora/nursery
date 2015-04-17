package br.com.pandox.nursery.domain.alert.service;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.model.AlertEntity;
import br.com.pandox.nursery.domain.alert.model.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;


    @Override
    public Alert create(Alert alert) {
        AlertEntity entity = (AlertEntity) alert;
        return alertRepository.save(entity);
    }
}
