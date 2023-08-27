package com.giovannicarmo.springbatchtutorial.ultils;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.xml.StaxWriterCallback;

public class XmlCustomHeaderCallback implements StaxWriterCallback {

    private static final Logger log = LoggerFactory.getLogger(XmlCustomHeaderCallback.class);

    private final String TAG_HEADER_ROOT = "Header";
    private final String TAG_CREATED_AT = "CREATED_AT";
    private final String TAG_MSG_ID = "MESSAGE_ID";

    private final LocalDateTime createdAt = LocalDateTime.now();
    private final UUID messageId = UUID.randomUUID();

    public XmlCustomHeaderCallback() {
        super();
    }

    @Override
    public void write(XMLEventWriter writer) {

        XMLEventFactory factory = XMLEventFactory.newInstance();

        try {
            writer.add(factory.createStartElement("", "", TAG_HEADER_ROOT));

            writer.add(factory.createStartElement("", "", TAG_CREATED_AT));
            writer.add(factory.createCharacters(this.createdAt.toString()));
            writer.add(factory.createEndElement("", "", TAG_CREATED_AT));

            writer.add(factory.createStartElement("", "", TAG_MSG_ID));
            writer.add(factory.createCharacters(this.messageId.toString()));
            writer.add(factory.createEndElement("", "", TAG_MSG_ID));

            writer.add(factory.createEndElement("", "", TAG_HEADER_ROOT));
        } catch (XMLStreamException e) {
            log.error("Error writing XML Header: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
