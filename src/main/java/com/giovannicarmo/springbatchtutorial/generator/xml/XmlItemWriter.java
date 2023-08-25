package com.giovannicarmo.springbatchtutorial.generator.xml;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;

import com.giovannicarmo.springbatchtutorial.domain.dto.EmployeeDTO;

public class XmlItemWriter implements ItemWriter<EmployeeDTO> {

    private final XmlGenerator xmlGenerator;

    public XmlItemWriter(XmlGenerator xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

    @Override
    public void write(@NonNull Chunk<? extends EmployeeDTO> chunk) throws Exception {
        Set<EmployeeDTO> EmployeeDTOs = new HashSet<>(chunk.getItems());
        xmlGenerator.generate("EmployeeEvents", UUID.randomUUID().toString(), EmployeeDTOs);
    }
}
