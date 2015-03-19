package br.com.pandox.nursery.service;

import br.com.pandox.nursery.entity.Monitor;
import br.com.pandox.nursery.repository.MonitorRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private MonitorRepository repository;

    public List<Monitor> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public Monitor persist(Monitor monitor) {
        return repository.save(monitor);
    }


    public Monitor findById(Long id) {
        return repository.findOne(id);
    }
}
