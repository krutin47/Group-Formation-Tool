package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;

// Classes implementing this interface decide how the application will handle passwords with policies.
// The intent is that the implementation details are entirely hidden from the rest of
// the application. We do not know which matching algorithm is used.
public interface IPasswordPolicies {

    public boolean passwordCheckMinLength(String rawPassword, int minLength);
    public boolean passwordCheckMaxLength(String rawPassword, int maxLength);
    public boolean passwordCheckMinUppercaseCharacter(String rawPassword, int minUppercaseCharacter);
    public boolean passwordCheckMinLowercaseCharacter(String rawPassword, int minLowercaseCharacter);
    public boolean passwordCheckMinSpecialCharacter(String rawPassword, int minSpecialCharacter);
    public boolean passwordCheckNotAllowedCharacter(String rawPassword, String[] notAllowed);
    public boolean passwordCheckHistory(String rawPassword, int historyCount, long id, IUserPersistence userDB, IPasswordEncryption passwordEncryption);

}
