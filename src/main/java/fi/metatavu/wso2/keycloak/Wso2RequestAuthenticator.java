package fi.metatavu.wso2.keycloak;

import java.util.Set;

import org.jboss.logging.Logger;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.AdapterTokenStore;
import org.keycloak.adapters.AdapterUtils;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.OAuthRequestAuthenticator;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.RequestAuthenticator;
import org.keycloak.adapters.spi.AuthOutcome;
import org.keycloak.adapters.spi.HttpFacade;

/**
 * WSO2 mediator implementation of Keycloak request authenticator.
 * 
 * Implementation contains only necessary features for validating bearer tokens
 * 
 * @author Antti Leppä
 */
public class Wso2RequestAuthenticator extends RequestAuthenticator {

  private static Logger log = Logger.getLogger(Wso2RequestAuthenticator.class);
  
  /**
   * Constructor
   * 
   * @param facade facade
   * @param deployment deployment
   * @param tokenStore token store
   * @param sslRedirectPort ssl redirect port
   */
  public Wso2RequestAuthenticator(HttpFacade facade, KeycloakDeployment deployment, AdapterTokenStore tokenStore, int sslRedirectPort) {
    super(facade, deployment, tokenStore, sslRedirectPort);
  }
  
  @Override
  public AuthOutcome authenticate() {
    try {
      return super.authenticate();
    } catch (Throwable e) {
      log.error("OAuth authentication failed", e); 
    }
    
    return AuthOutcome.FAILED;
  }
  
  @Override
  protected OAuthRequestAuthenticator createOAuthAuthenticator() {
    return new OAuthRequestAuthenticator(this, facade, deployment, sslRedirectPort, tokenStore);
  }

  @Override
  protected void completeOAuthAuthentication(final KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal) {
    final RefreshableKeycloakSecurityContext securityContext = principal.getKeycloakSecurityContext();
    final Set<String> roles = AdapterUtils.getRolesFromSecurityContext(securityContext);
    OidcKeycloakAccount account = new Wso2OidcKeycloakAccount(principal, roles, securityContext);
    this.tokenStore.saveAccountInfo(account);
  }

  @Override
  protected void completeBearerAuthentication(KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal, String method) {
  }

  @Override
  protected String changeHttpSessionId(boolean create) {
    return null;
  }

}
