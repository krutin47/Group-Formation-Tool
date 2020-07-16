package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultPasswordPolicies implements IPasswordPolicies {

    @Override
    public boolean passwordCheckMinLength(String rawPassword, int minLength) {
        SystemConfig.instance().getLOG().info("Password Check MinLength");
        SystemConfig.instance().getLOG().debug("checking Parameter rawPassword :: " + rawPassword.isEmpty());
        SystemConfig.instance().getLOG().debug("checking Parameter minLength :: " + minLength);
        return rawPassword.length() >= minLength;
    }

    @Override
    public boolean passwordCheckMaxLength(String rawPassword, int maxLength) {
        SystemConfig.instance().getLOG().info("Password Check MaxLength");
        SystemConfig.instance().getLOG().debug("checking Parameter rawPassword :: " + rawPassword.isEmpty());
        SystemConfig.instance().getLOG().debug("checking Parameter maxLength :: " + maxLength);
        return rawPassword.length() <= maxLength;
    }

    @Override
    public boolean passwordCheckMinUppercaseCharacter(String rawPassword, int minUppercaseCharacters) {
        SystemConfig.instance().getLOG().info("Password Check MinUppercaseCharacter");
        SystemConfig.instance().getLOG().debug("checking Parameter rawPassword :: " + rawPassword.isEmpty());
        SystemConfig.instance().getLOG().debug("checking Parameter minUppercaseCharacters :: " + minUppercaseCharacters);

        List<String> upperCh = new ArrayList<>(rawPassword.length());
        String[] ch = rawPassword.split("");
        for (String character : ch){
            if(character.matches("[A-Z]+")) {
                upperCh.add(character);
            }
        }
        SystemConfig.instance().getLOG().debug("checking min uppercase character :: " + upperCh.size());

        return upperCh.size() >= minUppercaseCharacters;
    }

    @Override
    public boolean passwordCheckMinLowercaseCharacter(String rawPassword, int minLowercaseCharacters) {
        SystemConfig.instance().getLOG().info("Password Check MinLowercaseCharacter");
        SystemConfig.instance().getLOG().debug("checking Parameter rawPassword :: " + rawPassword.isEmpty());
        SystemConfig.instance().getLOG().debug("checking Parameter minLowercaseCharacters :: " + minLowercaseCharacters);

        List<String> lowerCh = new ArrayList<>(rawPassword.length());
        String[] ch = rawPassword.split("");

        for (String character : ch){
            if(character.matches("[a-z]+")) {
                lowerCh.add(character);
            }
        }
        SystemConfig.instance().getLOG().debug("checking min lowercase character :: " + lowerCh.size());

        return lowerCh.size() >= minLowercaseCharacters;
    }

    @Override
    public boolean passwordCheckMinSpecialCharacter(String rawPassword, int minSpecialCharacter) {
        SystemConfig.instance().getLOG().info("Password Check MinSpecialCharacter");
        SystemConfig.instance().getLOG().debug("checking Parameter rawPassword :: " + rawPassword.isEmpty());
        SystemConfig.instance().getLOG().debug("checking Parameter minSpecialCharacter :: " + minSpecialCharacter);

        List<String> specialCh = new ArrayList<>(rawPassword.length());
        String[] ch = rawPassword.split("");

        for (String character : ch){
            System.out.println(!character.matches("[A-Za-z0-9 ]+"));
            if(!character.matches("[A-Za-z0-9 ]+")) {
                specialCh.add(character);
            }
        }
        SystemConfig.instance().getLOG().debug("checking min lowercase character :: " + specialCh.size());

        return specialCh.size() >= minSpecialCharacter;
    }

    @Override
    public boolean passwordCheckNotAllowedCharacter(String rawPassword, String[] notAllowed) {
        SystemConfig.instance().getLOG().info("Password Check MinSpecialCharacter");
        SystemConfig.instance().getLOG().debug("checking Parameter rawPassword :: " + rawPassword.isEmpty());
        SystemConfig.instance().getLOG().debug("checking Parameter notAllowed :: " + notAllowed.length);

        String[] ch = rawPassword.split("");
        System.out.println(Arrays.toString(notAllowed));

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

        SystemConfig.instance().getLOG().debug("checking Parameter rawPassword :: " + rawPassword.isEmpty());
        SystemConfig.instance().getLOG().debug("checking Parameter historyCount :: " + historyCount);
        SystemConfig.instance().getLOG().debug("checking Parameter id :: " + id);

        List<String> oldPasswords = userDB.fetchOldPasswords(id, historyCount);

        for(String oldPassword : oldPasswords){
            if(passwordEncryption.matches(rawPassword, oldPassword)){
                return true;
            }
        }
        return false;
    }
}