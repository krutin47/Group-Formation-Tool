package CSCI5308.GroupFormationTool.Utils;

public interface IJwtTokenGenerator {
    public String createJWT(String id, String issuer, String subject, long ttlMillis);
    public void parseJWT(String jwt);
}
