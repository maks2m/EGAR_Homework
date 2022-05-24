package ru.elkin.egar.logic;

public class FindString {
    private String findString;
    private String rootString;


    public boolean find(){
        int index = rootString.indexOf(findString);
        return index != -1;
    }

    public String getFindString() {
        return findString;
    }

    public <T> void setFindString(String findString) {
        this.findString = findString.toString();
    }

    public String getRootString() {
        return rootString;
    }

    public <S> void setRootString(String rootString) {
        this.rootString = rootString.toString();
    }
}