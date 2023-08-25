package com.giovannicarmo.springbatchtutorial.generator.xml;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.giovannicarmo.springbatchtutorial.domain.dto.EmployeeDTO;

import jakarta.xml.bind.JAXBException;

@Service
public class XmlGenerator {
    public void generate(String rootElementName, String operationId, Set<EmployeeDTO> employeeVOs)
            throws JAXBException {

        // XmlTemplate<EmployeeDTO> xmlWrapper = new XmlEmployee(new
        // Header(operationId), employeeVOs);

        // JAXBContext jaxbContext = JAXBContext.newInstance(XmlEmployee.class,
        // EmployeeDTO.class);
        // Marshaller marshaller = jaxbContext.createMarshaller();
        // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // File xmlFile = new File("employees.xml");
        // marshaller.marshal(xmlWrapper, xmlFile);

        System.out.println("XML file generated successfully.");
    }
}
