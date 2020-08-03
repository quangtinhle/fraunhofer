
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


    public class Client {

        public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
            //get the localhost IP address, if server is running on some other IP
            //InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            for(int i=0; i<30;i++){
                socket = new Socket("demo.server",8080);
                oos = new ObjectOutputStream(socket.getOutputStream());
                //System.out.println("Sending request to Socket Server");
                if(i==29)oos.writeObject("exit");
                else oos.writeObject(""+i);
                ois = new ObjectInputStream(socket.getInputStream());
                String message = (String) ois.readObject();
                System.out.println("Message: " + message);
                ois.close();
                oos.close();
                Thread.sleep(2000);
            }
        }
    }

