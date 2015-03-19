package br.com.pandox.nursery.domain.monitor.factory;

import br.com.pandox.nursery.domain.monitor.model.DataTO;
import br.com.pandox.nursery.domain.monitor.model.Model;

public interface Factory<MODEL extends Model, DTO extends DataTO> {

    MODEL fabric(DTO dto);

    DTO fabric(MODEL model);
}
