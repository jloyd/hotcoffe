package br.com.goals.hotcoffe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FakeRequest implements HttpServletRequest{
	private HashMap<String,String> parameter= new HashMap<String,String>();
	private FakeSession fakeSession = new FakeSession();
	
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getDateHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getHeader(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public Enumeration getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public Enumeration getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getIntHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public HttpSession getSession() {
		return fakeSession;
	}

	
	public HttpSession getSession(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public Enumeration getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setParameter(String nome,String valor){
		parameter.put(nome, valor);
	}
	
	public String getParameter(String arg0) {
		return parameter.get(arg0);
	}

	@SuppressWarnings("unchecked")
	
	public Map getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public Enumeration getParameterNames() {
		Vector<String> b = new Vector<String>();
		b.addAll(parameter.keySet());
		return b.elements();
	}

	
	public String[] getParameterValues(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}
	
}