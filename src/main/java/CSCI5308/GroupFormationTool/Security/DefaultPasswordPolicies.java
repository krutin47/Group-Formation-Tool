package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;

import java.util.ArrayList;
import java.util.List;

public class DefaultPasswordPolicies implements IPasswordPolicies {

    @Override
    public boolean passwordCheckMinLength(String rawPassword, int minLength) {
        return rawPassword.length() >= minLength;
    }

    @Override
    public boolean passwordCheckMaxLength(String rawPassword, int maxLength) {
        return rawPassword.length() <= maxLength;
    }

    @Override
    public boolean passwordCheckMinUppercaseCharacter(String rawPassword, int minUppercaseCharacters) {
        List<String> upperCh = new ArrayList<>(rawPassword.length());
        String[] ch = rawPassword.split("");

        for (String character : ch){
            if(character.matches("[A-Z]+")) {
                upperCh.add(character);
            }
        }

        return upperCh.size() >= minUppercaseCharacters;
    }

    @Override
    public boolean passwordCheckMinLowercaseCharacter(String rawPassword, int minLowercaseCharacters) {
        List<String> lowerCh = new ArrayList<>(rawPassword.length());
        String[] ch = rawPassword.split("");

        for (String character : ch){
            if(character.matches("[a-z]+")) {
                lowerCh.add(character);
            }
        }

        return lowerCh.size() >= minLowercaseCharacters;
    }

    @Override
    public boolean passwordCheckMinSpecialCharacter(String rawPassword, int minSpecialCharacter) {
        List<String> specialCh = new ArrayList<>(rawPassword.length());
        String[] ch = rawPassword.split("");

        for (String character : ch){
            if(!character.matches("[A-Za-z0-9 ]*")) {
                specialCh.add(character);
            }
        }

        return specialCh.size() >= minSpecialCharacter;
    }

    @Override
    public boolean passwordCheckNotAllowedCharacter(String rawPassword, String[] notAllowed) {
        String[] ch = rawPassword.split("");

        for (String character : ch){
            for(String notAllowedCharacter : notAllowed){
                if(character.equals(notAllowedCharacter)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean passwordCheckHistory(String rawPassword, int historyCount, long id, IUserPersistence userDB, IPasswordEncryption passwordEncryption) {
        List<String> oldPasswords = userDB.fetchOldPasswords(id, historyCount);

        for(String oldPassword : oldPasswords){
            if(passwordEncryption.matches(rawPassword, oldPassword)){
                return true;
            }
        }
        return false;
    }
}
