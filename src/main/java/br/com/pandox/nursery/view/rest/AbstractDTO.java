package br.com.pandox.nursery.view.rest;

import br.com.pandox.nursery.DataTransferObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AbstractDTO implements DataTransferObject {

    public AbstractDTO() {
        links = new ArrayList<>();
    }

    private List<Link> links;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        this.links.add(link);
    }
}
