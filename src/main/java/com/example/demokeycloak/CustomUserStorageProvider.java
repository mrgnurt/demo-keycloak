package com.example.demokeycloak;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import org.keycloak.storage.UserStorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Service
public class CustomUserStorageProvider implements UserLookupProvider, UserStorageProvider,
        CredentialInputValidator,
        UserQueryProvider {

    private KeycloakSession ksession;
    private ComponentModel model;

    @Autowired
    private UserRepository userRepository;


    public CustomUserStorageProvider(KeycloakSession ksession, ComponentModel model) {
        this.ksession = ksession;
        this.model = model;
    }

    @Override
    public boolean supportsCredentialType(String s) {
        return false;
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String s) {
        return false;
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        String userName = userModel.getUsername();
        var user = userRepository.findByUserName(userName);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String s) {
        User user = userRepository.findById(Long.parseLong(s)).get();
        return mapUser(realmModel, new CustomUser(ksession, realmModel, model, user.getUserName(), null, "fsd", "fsd", null));
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String s) {
        var user = userRepository.findByUserName(s);
        return mapUser(realmModel, new CustomUser(ksession, realmModel, model, user.getUserName(), " ", "fsd", "fsd", null));
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String s) {
        return null;
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, String s, Integer integer, Integer integer1) {
        return null;
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, Map<String, String> map, Integer integer, Integer integer1) {
        return null;
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer integer, Integer integer1) {
        return null;
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String s, String s1) {
        return null;
    }

    private UserModel mapUser(RealmModel realm, CustomUser data) {
        CustomUser user = new CustomUser.Builder(ksession, realm, model, data.getUsername())
                .email(data.getEmail())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .build();
        return user;
    }

}
