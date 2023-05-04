//package org.hello.jpa.member.domain2;
//
//import javax.persistence.*;
//
//
//@Entity //필수
//public class Member {
//
//    @Id @GeneratedValue
//    @Column(name ="MEMBER_ID")
//    private Long id;
//
//    @Column(name = "USERNAME")
//    private String username;
//
//    @ManyToOne
//    @JoinColumn(name="TEAM_iD",insertable = false , updatable = false)
//    private Team team;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}
