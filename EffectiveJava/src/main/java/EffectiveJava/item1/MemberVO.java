package EffectiveJava.item1;

import java.util.Objects;

public class MemberVO {
    private String username;
    private String email;
    private String password;

    private MemberVO(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public MemberVO build() {
            return new MemberVO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberVO memberVO = (MemberVO) o;
        return Objects.equals(username, memberVO.username) &&
                Objects.equals(email, memberVO.email) &&
                Objects.equals(password, memberVO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password);
    }
}
