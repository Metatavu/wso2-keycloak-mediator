package fi.metatavu.wso2.keycloak;

import javax.security.cert.X509Certificate;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.OIDCHttpFacade;

/**
 * WSO2 mediator implementation of Keycloak HTTP Facade.
 * 
 * Implementation contains only necessary features for validating bearer tokens
 * 
 * @author Antti Leppä
 */
public class Wso2OIDCHttpFacade implements OIDCHttpFacade {

  private Request request;

  /**
   * Constructor
   * 
   * @param request request object
   */
  public Wso2OIDCHttpFacade(Request request) {
    super();
    this.request = request;
  }

  public Request getRequest() {
    return request;
  }

  public Response getResponse() {
    return null;
  }

  public X509Certificate[] getCertificateChain() {
    return null;
  }

  public KeycloakSecurityContext getSecurityContext() {
    return null;
  }

}
