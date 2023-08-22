package com.giovannicarmo.springbatchtutorial.generator.xml;

import java.io.File;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.giovannicarmo.springbatchtutorial.generator.valueobject.EmployeeVO;
import com.giovannicarmo.springbatchtutorial.generator.valueobject.Header;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Service
public class XmlGenerator {
    public void generate(String operationId, Set<EmployeeVO> employeeVOs) throws JAXBException {

        XmlWrapper<EmployeeVO> xmlWrapper = new XmlWrapper<>(new Header(operationId), employeeVOs);

        JAXBContext jaxbContext = JAXBContext.newInstance(XmlWrapper.class, EmployeeVO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File xmlFile = new File("employees.xml");
        marshaller.marshal(xmlWrapper, xmlFile);

        System.out.println("XML file generated successfully.");
    }
}
