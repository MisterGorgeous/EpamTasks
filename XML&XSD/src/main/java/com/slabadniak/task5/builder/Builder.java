package com.slabadniak.task5.builder;

import com.slabadniak.task5.entityes.Jorney;
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

public abstract class Builder {
    private static final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static final String schemaName = "file/jorney-shema.xsd";
    protected static final String filename = "file/jorney.xml";  //to use in subclasses

    private void validate(){
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            // create shema
            Schema schema = factory.newSchema(schemaLocation);
            // create validator
            Validator validator = schema.newValidator();
            // check xml
            Source source = new StreamSource(filename);
            validator.validate(source);
            System.out.println(filename + " is valid.");
        } catch (SAXException e) {
            System.err.print("validation " + filename + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            System.err.print(filename + " is not valid because "
                    + e.getMessage());
        }
    }

    public ArrayList<Jorney> create(){
        validate();
        return parse();
    }

    abstract ArrayList<Jorney> parse();

}
