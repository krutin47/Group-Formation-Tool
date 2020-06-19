package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.Security.DefaultPasswordPolicies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
class DefaultPasswordPoliciesTest {

    @Test
    void passwordCheckMinLength() {
        DefaultPasswordPolicies passwordPolicies = new DefaultPasswordPolicies();
        Assert.isTrue(passwordPolicies.passwordCheckMinLength("This_is-me-The-P@ssword", 6),
                "Password is less than minimum length");
    }

    @Test
    void passwordCheckMaxLength() {
        DefaultPasswordPolicies passwordPolicies = new DefaultPasswordPolicies();
        Assert.isTrue(passwordPolicies.passwordCheckMaxLength("This_is-me-The-P@ssword", 25),
                "Password is greater than maximum length");
    }

    @Test
    void passwordCheckMinUppercaseCharacter() {
        DefaultPasswordPolicies passwordPolicies = new DefaultPasswordPolicies();
        Assert.isTrue(passwordPolicies.passwordCheckMinUppercaseCharacter("This_is-me-The-P@ssword", 2),
                "Password is less than minimum uppercase Characters");
    }

    @Test
    void passwordCheckMinLowercaseCharacter() {
        DefaultPasswordPolicies passwordPolicies = new DefaultPasswordPolicies();
        Assert.isTrue(passwordPolicies.passwordCheckMinLowercaseCharacter("This_is-me-The-P@ssword", 6),
                "Password is less than minimum lowercase Characters");
    }

    @Test
    void passwordCheckMinSpecialCharacter() {
        DefaultPasswordPolicies passwordPolicies = new DefaultPasswordPolicies();
        Assert.isTrue(passwordPolicies.passwordCheckMinSpecialCharacter("This_is-me-The-P@ssword", 1),
                "Password is less than minimum lowercase Characters");
    }

    @Test
    void passwordCheckNotAllowedCharacter() {
        DefaultPasswordPolicies passwordPolicies = new DefaultPasswordPolicies();
        String[] test = {"#","!","@"};
        Assert.isTrue(passwordPolicies.passwordCheckNotAllowedCharacter("This_is-me-The-P@ssword", test),
                "Password is less than minimum lowercase Characters");
    }

    @Test
    void passwordCheckHistory() {
        IUserPersistence userDBMock = new UserDBMock();
        Assertions.assertEquals( "password", userDBMock.fetchOldPasswords(1,1).get(0));
    }
}