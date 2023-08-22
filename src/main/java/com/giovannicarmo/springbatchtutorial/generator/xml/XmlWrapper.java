package com.giovannicarmo.springbatchtutorial.generator.xml;

import java.util.Set;

import com.giovannicarmo.springbatchtutorial.generator.valueobject.Header;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlWrapper<T> {

    @XmlElement(name = "Header")
    private Header header;

    @XmlElement(name = "items")
    private Set<T> items;

    public XmlWrapper() {
    }

    public XmlWrapper(Header header, Set<T> items) {
        this.header = header;
        this.items = items;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Set<T> getItems() {
        return items;
    }

    public void setItems(Set<T> items) {
        this.items = items;
    }
}
