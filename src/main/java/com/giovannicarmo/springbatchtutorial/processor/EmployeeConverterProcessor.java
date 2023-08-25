package com.giovannicarmo.springbatchtutorial.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.giovannicarmo.springbatchtutorial.domain.dto.EmployeeDTO;
import com.giovannicarmo.springbatchtutorial.domain.entity.Employee;
import com.giovannicarmo.springbatchtutorial.generator.xml.XmlTemplate;
import com.giovannicarmo.springbatchtutorial.mapper.EmployeeMapper;

public class EmployeeConverterProcessor implements ItemProcessor<Employee, XmlTemplate<EmployeeDTO>> {

    @Override
    @Nullable
    public XmlTemplate<EmployeeDTO> process(@NonNull Employee item) throws Exception {
        XmlTemplate<EmployeeDTO> template = new XmlTemplate<EmployeeDTO>();

        Set<EmployeeDTO> x = new HashSet<EmployeeDTO>();

        x.add(EmployeeMapper.INSTANCE.toDTO(item));

        template.setItems(x);

        return template;
    }

}
