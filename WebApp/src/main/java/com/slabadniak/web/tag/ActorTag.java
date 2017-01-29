package com.slabadniak.web.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ActorTag extends TagSupport {
    @Override
    public int doStartTag() throws JspTagException {

        try {
            JspWriter out = pageContext.getOut();
            out.write("<div class='actor col-md-4 col-lg-4 well' name='actor'>");
            out.write("<label class='control-label'>First name:</label>");
            out.write(" <input type='text' name='fname' placeholder='' class='input'>");
            out.write("<label class='control-label'>Seccond name:</label>");
            out.write("<input type='text' name='sname' placeholder='' class='input'>");
            out.write(" <label class='control-label'>Role:</label>");
            out.write(" <input type='text' name='role' placeholder='' class='input'>");
            out.write("  <label class='control-label'>Profession:</label>");
            out.write(" <input type='text' name='profession' placeholder='' class='input'>");
            out.write(" <label class='control-label'>Birthday:</label>");
            out.write(" <input type='date' name='birthday' value='2017-01-01' max='2017-01-01' min='1945-01-01'>");
            out.write("  <label class='control-label'>Birth Place:</label>");
            out.write("  <input type='text' name='birthplace' placeholder='' class='input'></div>';");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}