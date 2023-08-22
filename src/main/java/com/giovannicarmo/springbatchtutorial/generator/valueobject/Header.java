package com.giovannicarmo.springbatchtutorial.generator.valueobject;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Header {
    @XmlElement(name = "GENERATED_AT")
    private LocalDateTime generatedAt;
    @XmlElement(name = "OPERATION_ID")
    private String operationId;

    public Header() {
    }

    public Header(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt() {
        this.generatedAt = LocalDateTime.now();
    }
}
