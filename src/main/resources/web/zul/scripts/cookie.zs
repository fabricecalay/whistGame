import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

void setCookie(String name, String Content) {
	HttpServletResponse response = (HttpServletResponse)Executions.getCurrent().getNativeResponse();
	Cookie userCookie = new Cookie(name,Content);
	userCookie.setMaxAge(128000);  
	response.addCookie(userCookie);
}

String getCookie(String name){  
	HttpServletRequest request= (HttpServletRequest)Executions.getCurrent().getNativeRequest();
	Cookie [] cookies = request.getCookies();
    if (cookies!=null){
	for (int i=0; i<cookies.length; i++) {  
		if (name.equals(cookies[i].getName()))
			return cookies[i].getValue();
	}
	return "";
		}
        return "";
}
