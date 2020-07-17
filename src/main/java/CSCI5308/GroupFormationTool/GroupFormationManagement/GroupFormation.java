package CSCI5308.GroupFormationTool.GroupFormationManagement;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Answers.AnswerStudentMapper;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.*;

public class GroupFormation implements IGroupFormation{
    @Override
    @SuppressWarnings("unchecked")
    public List<Group> createGroup(List<AnswerStudentMapper> answerStudentMapper, int groupSize) {

        if(answerStudentMapper.size() == 0){
            return null;
        }

        IUserPersistence userPersistence = SystemConfig.instance().getUserDB();
        int noOfResponses = answerStudentMapper.size();
        long[] userIDs = new long[noOfResponses];
        int noOfQuestions = answerStudentMapper.get(0).getAnswers().size();
        String[][] answersList = new String[noOfResponses][noOfQuestions];
        int[][] similarityMatrix = new int[noOfResponses][noOfResponses];
        ArrayList<Integer> selectedUsers = new ArrayList<Integer>();
        int totalGroup;
        int grpCalculationTemp;
        List<User> students;
        List<Group> finalGroupList = new ArrayList<>();
        User tempUser = new User();

        grpCalculationTemp = noOfResponses % groupSize;
        System.out.println(grpCalculationTemp);
        if (grpCalculationTemp == 0){
            totalGroup = noOfResponses/groupSize;
        }
        else {
            totalGroup = (noOfResponses/groupSize)+1;
        }
        System.out.println("Total Group: "+totalGroup);

        for(int i=0; i<noOfResponses; i++){
            userIDs[i] = answerStudentMapper.get(i).getUserID();
            System.out.println(userIDs[i]);
        }

        for(int i = 0; i < noOfResponses; i++){
            for (int j = 0; j < noOfQuestions; j++){
                answersList[i][j] = answerStudentMapper.get(i).getAnswers().get(j).getAnswerValue();
            }
        }

        for (int i = 0; i < noOfResponses; i++){
            for (int j = 0; j < noOfResponses; j++){
                int count = 0;
                for (int k = 0; k < noOfQuestions; k++){
                    if (answersList[i][k].equalsIgnoreCase(answersList[j][k])){
                        count++;
                    }
                }
                if (i == j){
                    similarityMatrix[i][i] = 0;
                }
                else {
                    similarityMatrix[i][j] = count;
                }
            }
        }

        int tempIndex;
        for (int i = 0; i < noOfResponses - 1; i++){
            int[] tempGrpArray = new int[groupSize];
            tempIndex = 0;
            int tempColumnIndex = i+1;
            students = new ArrayList<User>();
            if (selectedUsers.contains(i)){
                continue;
            }
            tempGrpArray[0] = i;
            for (int j = 1; j < groupSize; j++){
                tempGrpArray[j] = 0;
            }
            for (int j = i+1; j < noOfResponses-1; j++){
                if (selectedUsers.contains(j)){
                    continue;
                }
                tempColumnIndex = j;
                if(similarityMatrix[i][j]<similarityMatrix[i][j+1]){
                    tempColumnIndex = j+1;
                }
                for (int k = 0; k < groupSize - 1; k++){
                    if (tempGrpArray.length == 2){
                        tempGrpArray[1] = tempColumnIndex;
                    }
                    else{
                        tempIndex = k;
                        if(tempGrpArray[k] > tempGrpArray[k+1]){
                            tempIndex = k+1;
                        }
                    }
                }
                tempGrpArray[tempIndex] = tempColumnIndex;
                /*for (int k = 0; k < groupSize - 1; k++){
                        if (tempGrpArray.length == 2){
                            k = 1;
                            continue;
                        }
                        else {
                            if(tempGrpArray[k]>tempGrpArray[k+1]){
                                tempIndex = k+1;
                            }
                        }
                    }*/
                //System.out.println("temp Index: " + tempIndex);
                //tempGrpArray[tempIndex] = j;
                /*
                for (int k = 0; k < groupSize - 1; k++){
                    System.out.println("tempArray[k]: "+ tempGrpArray[k]);
                    selectedUsers.add(tempGrpArray[k]);
                    userPersistence.loadUserByID(userIDs[k], tempUser);
                    students.add(tempUser);
                }
                finalGroupList.add(new Group(i, students));*/
            }
            for(int k = 0; k < groupSize; k++){
                selectedUsers.add(tempGrpArray[k]);
                userPersistence.loadUserByID(userIDs[k], tempUser);
                students.add(tempUser);
                System.out.println("Users:" + tempUser.getFirstName());
            }
            finalGroupList.add(new Group(i, students));
        }
        finalGroupList.size();
        System.out.println("Group Size:" + finalGroupList.size());
        return finalGroupList;
    }

    @Override
    public List<Group> createRandomGroup(List<AnswerStudentMapper> answerStudentMapper, int groupSize){

        IUserPersistence userPersistence = SystemConfig.instance().getUserDB();
        List<User> users = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        long[] userID = new long[answerStudentMapper.size()];

        if(answerStudentMapper.size() == 0){
            return null;
        }

        for(int i=0; i < answerStudentMapper.size(); i++){
            userID[i] = answerStudentMapper.get(i).getUserID();
        }

        for (int i = 0; i < answerStudentMapper.size(); i++){
            User tempUser = new User();
            userPersistence.loadUserByID(userID[i], tempUser);
            users.add(tempUser);
        }

        Collections.shuffle(users);

        int groupNumber = 1;
        int i = 0;
        while (i < users.size()){
            List<User> tempUser = new ArrayList<>();
            for (int j = 0; j < groupSize && i < users.size(); j++) {
                tempUser.add(users.get(i));
                //System.out.println(tempUser.get(j).getFirstName());
                i++;
            }
            groups.add(new Group(groupNumber, tempUser));
            groupNumber++;
        }
        return groups;
    }
}
