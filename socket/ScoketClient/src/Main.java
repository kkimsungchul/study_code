import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BufferedReader in = null;
        PrintWriter out = null;

        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try{
            socket= new Socket("127.0.0.1",8000);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while(true){
                System.out.println("Send Message : ");
                String outputMessage = scanner.nextLine();
                out.println(outputMessage);
                out.flush();
                if("exit".equalsIgnoreCase(outputMessage)){
                    break;
                }

                String inputMessage = in.readLine();
                System.out.println("From Server : " + inputMessage);
                if("exit".equalsIgnoreCase("inputMessage")){
                    break;
                }



            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{

                scanner.close();
                socket.close();;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
