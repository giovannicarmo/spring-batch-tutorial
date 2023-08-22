package com.giovannicarmo.springbatchtutorial.generator.valueobject;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Header {
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
