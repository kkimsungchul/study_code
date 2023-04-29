package org.hello.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity //필수
@Table(name="MEMBER")//테이블명이 다를 때 해당 테이블명으로 지정
public class Member {
    @Id
    @Column(name="id")//컬럼명이 다를때 해당 컬럼명으로 지정
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
