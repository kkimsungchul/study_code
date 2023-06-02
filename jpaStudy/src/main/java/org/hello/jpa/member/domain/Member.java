package org.hello.jpa.member.domain;

import org.hello.jpa.member.domain.embedded.Address;
import org.hello.jpa.member.domain.embedded.Period;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity //필수
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name ="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city" , column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name="street" , column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode" , column = @Column(name = "WORK_ZIPCODE")),
    })
    private Address workAddress;


//    @Column(name="TEAM_ID")
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="TEAM_ID")
    private Team team;

//    @OneToOne
//    @JoinColumn(name ="LOCKER_ID")
//    private Locker locker;
//
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
