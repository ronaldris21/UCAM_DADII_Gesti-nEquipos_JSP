package edu.ucam.tags;

import java.io.IOException;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class RequestErrorMessageTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		
		String mensaje = (String) pageContext.getRequest().getAttribute("ERROR_REQUEST");
		if(mensaje!=null)
			try {
				pageContext.getOut().append("<p>"+mensaje+"</p>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return EVAL_BODY_INCLUDE;
		
	}
	

}
