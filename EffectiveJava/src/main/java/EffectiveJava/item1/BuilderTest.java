package EffectiveJava.item1;

public class BuilderTest {
    public static void main(String[]args){
        UserVO userVO = UserVO
                .builder()
                .setAge(33)
                .setName("김성철")
                .build();
        System.out.println(userVO);

        MemberVO m = new MemberVO.Builder()
                .setEmail("메일주소")
                .setPassword("비밀번호")
                .setUsername("김성철")
                .build();

        BoardVO b = new BoardVO.Builder()
                .setContent("내용")
                .setTitle("제목")
                .setSeq(1)
                .setWriter("작성자")
                .build();
        System.out.println("###");
        System.out.println(b.toString());
    }
}


