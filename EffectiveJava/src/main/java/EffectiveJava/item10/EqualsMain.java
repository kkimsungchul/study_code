package EffectiveJava.item10;

public class EqualsMain {

    public static void main(String[]args){
        EqualsTestVO equalsTestVO1 = new EqualsTestVO(1,2,3,"김성철");
        EqualsTestVO equalsTestVO2 = new EqualsTestVO(1,2,4,"김성철");

        EqualsTestVO equalsTestVO3 = new EqualsTestVO(1,2,4,"양혜은");
        EqualsTestVO equalsTestVO4 = new EqualsTestVO(1,2,4,"양혜은");

        System.out.println("### equalsTestVO1.equals(equalsTestVO2) : " + equalsTestVO1.equals(equalsTestVO2));
        System.out.println("### equalsTestVO3.equals(equalsTestVO4) : " + equalsTestVO3.equals(equalsTestVO4));
        System.out.println();
        

        System.out.println("### equalsTestVO1.equals(equalsTestVO3) : " + equalsTestVO1.equals(equalsTestVO3));
        System.out.println("### equalsTestVO2.equals(equalsTestVO4) : " + equalsTestVO2.equals(equalsTestVO4));



    }

}
