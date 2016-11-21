package com.slabadniak.task5.builder;

import com.slabadniak.task5.entities.Journey;
import com.slabadniak.task5.parser.AbstractParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//It is not completely a Pattern Builder, but with parametrization it's look better

public class Builder {
    private static final Logger LOGGER = LogManager.getLogger(Builder.class);
    private static final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static final String schemaName = "file/jorney-shema.xsd";
    protected static final String filename = "file/jorney.xml";     //to use in subclasses

    private void validate(){
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);   // create shema
            Validator validator = schema.newValidator();        // create validator
            Source source = new StreamSource(filename);         // check xml
            validator.validate(source);
        } catch (SAXException|IOException e) {
            LOGGER.log(Level.FATAL, e);
            throw new RuntimeException();
        }
    }

    public <P extends AbstractParser> ArrayList<Journey> create(P parser){
        validate();
        return parse(parser);
    }

    public <P extends AbstractParser> ArrayList<Journey> parse(P parser) {
        parser.buildJourneys(filename);
        return parser.getJorneys();
    }

}
