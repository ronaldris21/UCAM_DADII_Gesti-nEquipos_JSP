package edu.ucam.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class TagControlAdmin extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	public int doStartTag() throws JspException {

		
		if (this.pageContext.getSession().getAttribute("EMAIL_LOGIN")==null) 
			return SKIP_BODY;
		if(!this.pageContext.getSession().getAttribute("EMAIL_LOGIN").equals("admin"))
			return SKIP_BODY;
		
		return EVAL_BODY_INCLUDE;
		
	}
	
	
	

}
