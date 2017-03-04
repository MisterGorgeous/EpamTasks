package com.slabadniak.web.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This tag is substitute  select tag to order movies on the main page.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class MovieOrderTag extends TagSupport {
    @Override
    public int doStartTag() throws JspTagException {

        String currentValue = (String)  pageContext.getSession().getAttribute("movieOrder");
        List<String> orders = new ArrayList<String>(){
            {
                add("Alphabetic");
                add("Start with highest rate");
                add("Start with lowest rate");
            }
        } ;

        try {
            JspWriter out = pageContext.getOut();
            out.write("<select id=\"sort\" name=\"order\">");
            out.write("<option>" + currentValue + "</option>");
            for(String otherValue : orders ){
                if(!currentValue.equals(otherValue)) {
                    out.write("<option>" + otherValue + "</option>");
                }
            }
            out.write(" </select>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
