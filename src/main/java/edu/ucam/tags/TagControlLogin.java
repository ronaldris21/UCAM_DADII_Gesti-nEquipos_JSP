package edu.ucam.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class TagControlLogin extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public int doStartTag() throws JspException {
		if(pageContext.getSession().getAttribute("EMAIL_LOGIN")!=null)
			return EVAL_BODY_INCLUDE;
		
		return SKIP_BODY;
		
	}
	

}
