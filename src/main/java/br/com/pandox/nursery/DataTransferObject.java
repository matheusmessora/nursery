package br.com.pandox.nursery;

import br.com.pandox.nursery.view.Link;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract interface DataTransferObject {

    void addLink(Link link);

    void setLinks(List<Link> links);

    List<Link> getLinks();
}
