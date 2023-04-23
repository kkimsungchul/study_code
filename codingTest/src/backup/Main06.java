package backup;

public class Main06 {
    public static void main(String[]args){
        int a= 25;
        int b=13;
        String init_password = "0001";
        String newPassword = String.valueOf(((a*Integer.parseInt(init_password)+b)%10000));

    }
}
