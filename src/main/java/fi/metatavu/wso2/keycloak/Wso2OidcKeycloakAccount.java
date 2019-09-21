package fi.metatavu.wso2.keycloak;

import java.security.Principal;
import java.util.Set;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.OidcKeycloakAccount;

/**
 * WSO2 mediator implementation of Keycloak account.
 * 
 * @author Antti Leppä
 */
public class Wso2OidcKeycloakAccount implements OidcKeycloakAccount {

  private Principal principal;
  private Set<String> roles;
  private KeycloakSecurityContext keycloakSecurityContext;

  /**
   * Constructor
   * 
   * @param principal principal
   * @param roles roles
   * @param keycloakSecurityContext context
   */
  public Wso2OidcKeycloakAccount(Principal principal, Set<String> roles, KeycloakSecurityContext keycloakSecurityContext) {
    super();
    this.principal = principal;
    this.roles = roles;
    this.keycloakSecurityContext = keycloakSecurityContext;
  }

  /**
   * Returns principal
   * 
   * @return principal
   */
  public Principal getPrincipal() {
    return principal;
  }

  /**
   * Returns roles
   * 
   * @return roles
   */
  public Set<String> getRoles() {
    return roles;
  }

  /**
   * Returns security context
   * 
   * @return security context
   */
  public KeycloakSecurityContext getKeycloakSecurityContext() {
    return keycloakSecurityContext;
  }

}
