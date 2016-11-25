package com.slabadniak.task5;

import com.slabadniak.task5.entity.Journey;
import com.slabadniak.task5.parser.AbstractParser;

import javax.xml.XMLConstants;
import java.util.ArrayList;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/

//It is not completely a Pattern Builder, but with parametrization it's look better

public class Builder {
   // private static final Logger LOGGER = LogManager.getLogger(Builder.class);
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
   // public static final String SHEMANAME = "file/journey-shema.xsd";
    public static final String FILENAME = "journey.xml";     //to use in subclasses

    public <Parser extends AbstractParser> ArrayList<Journey> create(Parser parser){
       // validate();
        return parse(parser);
    }

   /* private void validate(){
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        File schemaLocation = new File(SHEMANAME);
        try {
            Schema schema = factory.newSchema(schemaLocation);   // create shema
            Validator validator = schema.newValidator();        // create validator
            Source source = new StreamSource(FILENAME);         // check xml
            validator.validate(source);
        } catch (SAXException|IOException e) {
           // LOGGER.log(Level.FATAL, e);
            throw new RuntimeException();
        }
    }
*/
    private <Parser extends AbstractParser> ArrayList<Journey> parse(Parser parser) {
        parser.buildJourneys(FILENAME);
        return parser.getJorneys();
    }

}
