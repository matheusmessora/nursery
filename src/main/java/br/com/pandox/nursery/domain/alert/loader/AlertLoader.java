package br.com.pandox.nursery.domain.alert.loader;

import br.com.pandox.nursery.domain.alert.Alert;

import java.util.List;

public interface AlertLoader {

    List<Alert> loadByMetricId(Long id);
}
