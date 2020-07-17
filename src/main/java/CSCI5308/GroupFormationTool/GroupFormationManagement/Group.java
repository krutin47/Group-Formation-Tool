package CSCI5308.GroupFormationTool.GroupFormationManagement;

import CSCI5308.GroupFormationTool.AccessControl.User;
import java.util.List;

public class Group {
    private int groupNumber;
    private List<User> userArrayList;

    public Group(int groupNumber, List<User> userArrayList) {
        this.groupNumber = groupNumber;
        this.userArrayList = userArrayList;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(List<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}
