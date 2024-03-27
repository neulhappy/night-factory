package org.recorder.nightfactory.dto;

public class PasswordRequest {
    private Long id; // 게시글 ID
    private String password; // 비밀번호

    // 생성자, 게터 및 세터
    // 생성자
    public PasswordRequest() {
    }

    public PasswordRequest(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    // 게터 및 세터
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
