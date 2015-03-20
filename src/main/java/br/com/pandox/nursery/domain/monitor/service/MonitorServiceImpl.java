package br.com.pandox.nursery.domain.monitor.service;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.repository.entity.MonitorEntity;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorRepository repository;

    @Override public Monitor findByID(Long id) {
        return repository.findOne(id);
    }

    @Override public List<Monitor> findAll() {
        Iterable<MonitorEntity> all = repository.findAll();

        ImmutableList.Builder<Monitor> builder = ImmutableList.builder();
        ImmutableList<Monitor> monitors = builder.addAll(all).build();
        return monitors;
    }
}
