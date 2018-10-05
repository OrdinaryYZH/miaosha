package com.genericyzh.miaosha.user.model.vo;

public class UserVO {
    private String id;
    private String nickname;
    private String head;

    public UserVO() {
    }

    public UserVO(String id, String nickname, String head) {
        this.id = id;
        this.nickname = nickname;
        this.head = head;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
