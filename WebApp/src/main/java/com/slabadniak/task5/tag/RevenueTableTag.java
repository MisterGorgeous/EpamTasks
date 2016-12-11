package com.slabadniak.task5.tag;

import com.slabadniak.task5.Builder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class RevenueTableTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(Builder.class);
    private String head;
    private int rows;

    public void setHead(String head) {
        this.head = head;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<table border='1'><colgroup span='2' title='title' />");
            out.write("<caption>" + Locale.getDefault().getDisplayCountry() + "</caption>");
            out.write("<thead><tr><th scope='col'>" + head + "</th></tr></thead>");
            out.write("<tbody><tr><td>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspTagException {
        if (rows-- > 1) {
            try {
                pageContext.getOut().write("</td></tr><tr><td>");
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() {
        try {
            pageContext.getOut().write("</td></tr></tbody></table>");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return EVAL_PAGE;
    }
}
