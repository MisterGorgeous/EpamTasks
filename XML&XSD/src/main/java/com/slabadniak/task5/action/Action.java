package com.slabadniak.task5.action;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import com.slabadniak.task5.parser.DOMParser;
import com.slabadniak.task5.parser.StAXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import org.xml.sax.SAXException;

public class Action {
    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static void main(String[ ] args) {

       /* String filename = "data/students.xml";
        String schemaname = "data/students.xsd";
        String logname = "logs/log.txt";
        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
// установка проверки с использованием XSD
            schema = factory.newSchema(new File(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
// создание объекта-парсера
            SAXParser parser = spf.newSAXParser();
// установка обработчика ошибок и запуск
            parser.parse(filename, new StudentErrorHandler(logname));
            System.out.println(filename + " is valid");
        } catch (ParserConfigurationException e) {
            System.err.println(filename + " config error: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println(filename + " SAX error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }*/
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String filename = "file/jorney.xml";
        String schemaName = "file/jorney-shema.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
// создание схемы
            Schema schema = factory.newSchema(schemaLocation);
// создание валидатора на основе схемы
            Validator validator = schema.newValidator();
// проверка документа
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

       /* DOMParser dom = new DOMParser();
        dom.buildJorneys("file/jorney.xml");
        LOGGER.info(dom.getJorneys().toString());*/

       /*com.slabadniak.task5.parser.SAXParser saxBuilder = new com.slabadniak.task5.parser.SAXParser();
        saxBuilder.buildJorneys("file/jorney.xml");
        LOGGER.info(saxBuilder.getJorneys().toString());*/

        StAXParser staxBuilder = new StAXParser();
        staxBuilder.buildJorneys("file/jorney.xml");
        LOGGER.info(staxBuilder.getJorneys().toString());
    }






}
