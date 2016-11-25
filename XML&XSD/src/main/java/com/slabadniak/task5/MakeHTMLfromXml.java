package com.slabadniak.task5;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class MakeHTMLfromXml {
    public static final String HTMLNAME = "file/journey.html";
    public static final String XSLTNAME = "file/journey.xslt";

    public static void make(){
        try{
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer(new StreamSource(new File(XSLTNAME)));
            StreamSource ss = new StreamSource(new File(Builder.FILENAME));
            StreamResult sr = new StreamResult(new File(HTMLNAME));
            tf.transform(ss,sr);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
