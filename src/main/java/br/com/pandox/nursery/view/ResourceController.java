package br.com.pandox.nursery.view;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ResourceController<T> {


    public ResponseEntity<List<T>> findAll();

    public ResponseEntity<T> findById(@PathVariable Long id);


}
