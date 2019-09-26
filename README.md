# wso2-keycloak-mediator


## Example configuration

    <class name="fi.metatavu.wso2.keycloak.KeycloakMediator">
      <property name="authServerUrl" value="http://localhost:8080/auth"/>
      <property name="clientSecret" value="11112222-3333-4444-5555-666677778888"/>
      <property name="bearerOnly" value="true"/>
      <property name="resource" value="resource"/>
      <property name="sslRequired" value="EXTERNAL"/>
      <property name="realm" value="realm"/>
    </class>

    #!/bin/shcd /home/wso2carbon/extra-certs/FILES=*.crt
    
    keytool -importcert -noprompt -file $f -storepass wso2carbon -keystore /home/wso2carbon/wso2ei-6.5.0/repository/resources/security/client-truststore.jks -alias ${f}
    
    done