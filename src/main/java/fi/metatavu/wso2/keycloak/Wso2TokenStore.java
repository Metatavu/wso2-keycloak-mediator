package fi.metatavu.wso2.keycloak;

import org.keycloak.adapters.AdapterTokenStore;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.RequestAuthenticator;

/**
 * WSO2 mediator implementation of Keycloak token store.
 * 
 * Implementation as a no-op implementation and does not actually store any
 * tokens
 * 
 * @author Antti Leppä
 */
public class Wso2TokenStore implements AdapterTokenStore {

  /**
   * Constructor
   */
  public Wso2TokenStore() {
  }

  public void checkCurrentToken() {
  }

  public boolean isCached(RequestAuthenticator authenticator) {
    return false;
  }

  public void saveAccountInfo(OidcKeycloakAccount account) {
  }

  public void logout() {
  }

  public void refreshCallback(RefreshableKeycloakSecurityContext securityContext) {
  }

  public void saveRequest() {
  }

  public boolean restoreRequest() {
    return false;
  }

}
