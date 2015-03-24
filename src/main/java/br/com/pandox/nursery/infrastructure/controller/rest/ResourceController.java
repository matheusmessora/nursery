package br.com.pandox.nursery.infrastructure.controller.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ResourceController<T> {

    public ResponseEntity<T> findById(@PathVariable Long id);


}
