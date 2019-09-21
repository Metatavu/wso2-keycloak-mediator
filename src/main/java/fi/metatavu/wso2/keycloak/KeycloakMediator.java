package fi.metatavu.wso2.keycloak;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.AuthOutcome;
import org.keycloak.adapters.spi.HttpFacade.Request;
import org.keycloak.representations.adapters.config.AdapterConfig;

/**
 * Mediator for securing WSO2 endpoints with Keycloak tokens
 * 
 * @author Antti Leppä
 */
public class KeycloakMediator extends AbstractMediator {

  private String authServerUrl;
  private String clientSecret;
  private boolean bearerOnly;
  private String resource;
  private String sslRequired;
  private String realm;
  private boolean cors;

  /**
   * Mediate method
   * 
   * @param context context
   * @return whether user is allowed to continue
   */
  public boolean mediate(MessageContext context) {
    Axis2MessageContext messageContext = (Axis2MessageContext) context;

    Request request = new Wso2KeycloakRequest(getTransportHeaders(messageContext));
    Wso2OIDCHttpFacade facade = new Wso2OIDCHttpFacade(request);
    KeycloakDeployment deployment = getKeycloakDeployment();
    Wso2TokenStore tokenStore = new Wso2TokenStore();
    int sslRedirectPort = -1;

    Wso2RequestAuthenticator authenticator = new Wso2RequestAuthenticator(facade, deployment, tokenStore, sslRedirectPort);

    AuthOutcome outcome = authenticator.authenticate();

    return outcome == AuthOutcome.AUTHENTICATED;
  }

  /**
   * Returns "authServerUrl" setting value
   * 
   * @return "authServerUrl" setting value
   */
  public String getAuthServerUrl() {
    return authServerUrl;
  }

  /**
   * Setter for "authServerUrl" setting
   * 
   * @param authServerUrl setting value
   */
  public void setAuthServerUrl(String authServerUrl) {
    this.authServerUrl = authServerUrl;
  }

  /**
   * Returns "clientSecret" setting value
   * 
   * @return "clientSecret" setting value
   */
  public String getClientSecret() {
    return clientSecret;
  }

  /**
   * Setter for "clientSecret" setting
   * 
   * @param clientSecret setting value
   */
  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  /**
   * Returns "bearerOnly" setting value
   * 
   * @return "bearerOnly" setting value
   */
  public boolean getBearerOnly() {
    return bearerOnly;
  }

  /**
   * Setter for "bearerOnly" setting
   * 
   * @param bearerOnly setting value
   */
  public void setBearerOnly(boolean bearerOnly) {
    this.bearerOnly = bearerOnly;
  }

  /**
   * Returns "resource" setting value
   * 
   * @return "resource" setting value
   */
  public String getResource() {
    return resource;
  }

  /**
   * Setter for "resource" setting
   * 
   * @param resource setting value
   */
  public void setResource(String resource) {
    this.resource = resource;
  }

  /**
   * Returns "sslRequired" setting value
   * 
   * @return "sslRequired" setting value
   */
  public String getSslRequired() {
    return sslRequired;
  }

  /**
   * Setter for "sslRequired" setting
   * 
   * @param sslRequired setting value
   */
  public void setSslRequired(String sslRequired) {
    this.sslRequired = sslRequired;
  }

  /**
   * Returns "realm" setting value
   * 
   * @return "realm" setting value
   */
  public String getRealm() {
    return realm;
  }

  /**
   * Setter for "realm" setting
   * 
   * @param realm setting value
   */
  public void setRealm(String realm) {
    this.realm = realm;
  }
  
  /**
   * Setter for "cors" setting
   * 
   * @param cors setting value
   */
  public void setCors(boolean cors) {
    this.cors = cors;
  }
  
  /**
   * Returns "cors" setting value
   * 
   * @return "cors" setting value
   */
  public boolean getCors() {
    return cors;
  }

  /**
   * Returns headers from request 
   * 
   * @param messageContext context
   * @return headers from request 
   */
  @SuppressWarnings("unchecked")
  private Map<String, Object> getTransportHeaders(Axis2MessageContext messageContext) {
    return (Map<String, Object>) messageContext.getAxis2MessageContext().getProperty("TRANSPORT_HEADERS");
  }

  /**
   * Creates Keycloak configuration
   * 
   * @return Keycloak configuration
   */
  private KeycloakDeployment getKeycloakDeployment() {
    AdapterConfig adapterConfig = new AdapterConfig();
    Map<String, Object> credentials = new HashMap<>();
    
    if (StringUtils.isNotBlank(getClientSecret())) {
      credentials.put("secret", getClientSecret());
    }
    
    adapterConfig.setCors(getCors());
    adapterConfig.setRealm(getRealm());
    adapterConfig.setSslRequired(getSslRequired());
    adapterConfig.setResource(getResource());
    adapterConfig.setCredentials(credentials);
    adapterConfig.setConfidentialPort(0);
    adapterConfig.setAuthServerUrl(getAuthServerUrl());
    adapterConfig.setBearerOnly(getBearerOnly());

    return KeycloakDeploymentBuilder.build(adapterConfig);
  }

}
