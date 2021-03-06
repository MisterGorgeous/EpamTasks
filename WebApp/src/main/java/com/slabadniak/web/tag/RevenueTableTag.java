package com.slabadniak.web.tag;

import com.slabadniak.web.entity.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * This tag extend functionality of input tag. It helps to represent user's status.
 * @author Slabadniak Sergei
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RevenueTableTag extends TagSupport {
    private int index;

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public int doStartTag() throws JspTagException {
        List<User> users = (List<User>)  pageContext.getSession().getAttribute("users");
        User user = users.get(index);
        String userStatus = user.getStatus();
        int sliderValue = userStatus.equals("beginer") ? 1 : userStatus.equals("fan") ? 2 : 3;

        try {
            JspWriter out = pageContext.getOut();
            out.write("<input onchange=\"$(this).closest('form').submit();\" type='text' name='status'");
            out.write("data-provide='slider' data-slider-ticks='[1, 2, 3]'");
            out.write("data-slider-ticks-labels='[\"beginer\", \"fan\", \"expert\"]' data-slider-min='1'");
            out.write("data-slider-max='3' data-slider-step='1'");
            out.write("data-slider-value='" + sliderValue + "'");
            out.write("data-slider-tooltip='hide' />");

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}