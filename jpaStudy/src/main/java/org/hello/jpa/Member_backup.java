//package org.hello.jpa;
//
//import javax.persistence.*;
//import java.util.Date;
//
//
//@Entity //필수
////@Table(name="MEMBER")//테이블명이 다를 때 해당 테이블명으로 지정
//@SequenceGenerator(name="member_seq_generator" , sequenceName = "member_seq")
//public class Member_backup {
//    @Id
//    @Column(name="id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_seq_generator")
//    private Long id;
//
//    @Column(name="name")//컬럼명이 다를때 해당 컬럼명으로 지정, 객체는 username DB는 name
//    private String username;
//
//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date date;
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    @Lob
//    private String description;
//    //JPA는 기본 생성자가 있어야함
//
//    @Transient
//    private int temp;
//
//    public Member_backup() {
//    }
//
//    public Member_backup(Long id, String username) {
//        this.id = id;
//        this.username = username;
//    }
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
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public Date getLastModifiedDate() {
//        return lastModifiedDate;
//    }
//
//    public void setLastModifiedDate(Date lastModifiedDate) {
//        this.lastModifiedDate = lastModifiedDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
