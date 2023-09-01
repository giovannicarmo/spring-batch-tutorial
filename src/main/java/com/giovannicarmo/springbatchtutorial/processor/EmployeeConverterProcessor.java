package com.giovannicarmo.springbatchtutorial.processor;

import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.giovannicarmo.springbatchtutorial.domain.dto.EmployeeDTO;
import com.giovannicarmo.springbatchtutorial.domain.entity.Employee;
import com.giovannicarmo.springbatchtutorial.mapper.EmployeeMapper;
import com.giovannicarmo.springbatchtutorial.ultils.WrappedItem;

public class EmployeeConverterProcessor implements ItemProcessor<Employee, WrappedItem<Employee, String>> {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Override
    @Nullable
    public WrappedItem<Employee, String> process(@NonNull Employee item) throws Exception {
        EmployeeDTO employeeDTO = EmployeeMapper.INSTANCE.toDTO(item);

        // marshaling DTO
        StringWriter writer = new StringWriter();
        marshaller.marshal(employeeDTO, new StreamResult(writer));
        String marshaledItem = writer.toString();

        return new WrappedItem<>(item, marshaledItem);
    }
}
