package com.slabadniak.task5;

import com.slabadniak.task5.entity.Journey;
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
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    public static  String shemaname =  "S:\\git_rep\\Epam\\WebApp\\src\\main\\webapp\\xml\\journey-shema.xsd";
    public static  String filename ="S:\\git_rep\\Epam\\WebApp\\src\\main\\webapp\\xml\\journey.xml" ;
    public int index ;//to use in subclasses

    public Builder (String rootpath){
       /* shemaname = rootpath + "/xml/journey-shema.xsd";
        filename = rootpath + "/xml/journey.xml";*/
        index = 10;
    }
    public <Parser extends AbstractParser> ArrayList<Journey> create(Parser parser){
        validate();
        return parse(parser);
    }

    private void validate(){
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        File schemaLocation = new File(shemaname);
        try {
            Schema schema = factory.newSchema(schemaLocation);   // create shema
            Validator validator = schema.newValidator();        // create validator
            Source source = new StreamSource(filename);         // check xml
            validator.validate(source);
        } catch (SAXException |IOException e) {
            LOGGER.log(Level.FATAL, e);
            throw new RuntimeException();
        }
    }

    private <Parser extends AbstractParser> ArrayList<Journey> parse(Parser parser) {
        parser.buildJourneys(filename);
        return parser.getJorneys();
    }

}
