package fi.metatavu.wso2.keycloak;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.keycloak.adapters.spi.AuthenticationError;
import org.keycloak.adapters.spi.HttpFacade.Cookie;
import org.keycloak.adapters.spi.HttpFacade.Request;
import org.keycloak.adapters.spi.LogoutError;

/**
 * WSO2 mediator implementation of Keycloak HTTP Facade request.
 * 
 * Implementation contains only necessary features for validating bearer tokens
 * 
 * @author Antti Leppä
 */
public class Wso2KeycloakRequest implements Request {
  
  private Map<String, Object> headers;
  
  /**
   * Constructor
   * 
   * @param headers request headers
   */
  public Wso2KeycloakRequest(Map<String,Object> headers) {
    this.headers = headers;
  }

  public String getMethod() {
    return null;
  }

  public String getURI() {
    return null;
  }

  public String getRelativePath() {
    return null;
  }

  public boolean isSecure() {
    return false;
  }

  public String getFirstParam(String param) {
    return null;
  }

  public String getQueryParamValue(String param) {
    return null;
  }

  public Cookie getCookie(String cookieName) {
    return null;
  }

  public String getHeader(String name) {
    List<String> values = getHeaders(name);
    if (values.isEmpty()) {
      return null;
    }
    
    return values.get(0);
  }

  public List<String> getHeaders(String name) {
    Object value = headers.get(name);
    if (value instanceof String) {
      return Collections.singletonList((String) value);
    }
    
    return Collections.emptyList();
  }

  public InputStream getInputStream() {
    return null;
  }

  public InputStream getInputStream(boolean buffered) {
    return null;
  }

  public String getRemoteAddr() {
    return null;
  }

  public void setError(AuthenticationError error) {
  }

  public void setError(LogoutError error) {
  }

}
