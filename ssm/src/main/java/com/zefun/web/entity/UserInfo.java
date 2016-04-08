package com.zefun.web.entity;

public class UserInfo {
    private String userName;

    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }
    
    public UserInfo() {
        // TODO Auto-generated constructor stub
    }

    public UserInfo(String userName, String passWord) {
        super();
        this.userName = userName;
        this.passWord = passWord;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserInfo [userName=" + userName + ", passWord=" + passWord
                + "]";
    }
    
}