package com.giovannicarmo.springbatchtutorial.generator.xml;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlTemplate<T> {

    @XmlElement(name = "Header")
    private XmlTemplate.Header header;

    @XmlElement(name = "Item")
    private Set<T> items;

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {
        @XmlElement(name = "OPERATION_ID")
        private String operationId;

        @XmlElement(name = "GENERATED_AT")
        private String generatedAt;

        public Header() {
        }

        public Header(String operationId) {
            this.operationId = operationId;
            this.generatedAt = LocalDateTime.now().toString();
        }

        public String getOperationId() {
            return operationId;
        }

        public void setOperationId(String operationId) {
            this.operationId = operationId;
        }

        public String getGeneratedAt() {
            return generatedAt;
        }
    }

    public XmlTemplate() {
    }

    public XmlTemplate(Header header, Set<T> items) {
        this.header = header;
        this.items = items;
    }

    public XmlTemplate.Header getHeader() {
        return header;
    }

    public void setHeader(XmlTemplate.Header header) {
        this.header = header;
    }

    public Set<T> getItems() {
        return items;
    }

    public void setItems(Set<T> items) {
        this.items = items;
    }
}
