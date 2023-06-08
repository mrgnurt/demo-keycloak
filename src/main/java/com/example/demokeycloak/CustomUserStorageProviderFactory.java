package com.example.demokeycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

public class CustomUserStorageProviderFactory implements UserStorageProviderFactory<CustomUserStorageProvider> {

    @Override
    public CustomUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new CustomUserStorageProvider(keycloakSession, componentModel);
    }

    @Override
    public String getId() {
        return "custom-user-provider";
    }
}
