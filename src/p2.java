import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class p2 {
    static String receiveM;
    static String data;
    static StringBuffer temp;
    static String dataSend1="", dataSend2="", dataSend3="";

    /**
     * Read text file from directory
     */
    public static void readFile(String fileName){
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            // Read the file into String
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Get file info
     * Showing:
     * - file name
     * - file path
     * - file size
     */
    public static void getFileInfo(String fileName){
        File myObj = new File(fileName);
        if (myObj.exists()) {
            System.out.println("-----File Information-------");
            System.out.println("File name: " + myObj.getName()); //File name: f1.txt
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("File size: " + myObj.length() + " byte(s)"); //File size: 300 byte(s)
        }
        else {
            System.out.println("File does not exist.");
        }
    }

    /**
     * Export the data to the file
     */
    private static void writeFile(String fileName){
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(String.valueOf(temp));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Separate 300 bytes data into 3 parts
     * Each part is 100 bytes
     * @param data text file data
     */
    private static void dataProcess(String data){
        for (int i = 0; i < 100; i++){
            dataSend1 = dataSend1 + data.charAt(i);
        }
        for (int i = 100; i < 200; i++){
            dataSend2 = dataSend2 + data.charAt(i);
        }
        for (int i = 200; i < 300; i++){
            dataSend3 = dataSend3 + data.charAt(i);
        }
    }

    /**
     * Socket main program - server
     * @param port the Socket port
     */
    public static void server(int port) {
        try {
            // Create a new socket server object
            ServerSocket ss = new ServerSocket(port);
            System.out.println("---Server started---");

            // Establishes connection and showing client's IP address and port.
            Socket s = ss.accept();
            System.out.println("Client IP: " + s.getRemoteSocketAddress());
            System.out.println("Port: " + s.getPort());

            // Create an object for receiving data
            DataInputStream receive = new DataInputStream(s.getInputStream());

            // Create an object for sending data
            DataOutputStream send = new DataOutputStream(s.getOutputStream());

            // Assign the text file data to temporary area
            StringBuffer sb = new StringBuffer(data);

            // Receiving first 75 bytes data from p1 
            // and sending first 100 bytes to p2
            receiveM = receive.readUTF();
            sb.insert(0, receiveM);
            send.writeUTF(dataSend1);
            send.flush();

            // Receiving sencond 75 bytes data from p1 
            // and sending second 100 bytes to p2
            receiveM = receive.readUTF();
            sb.insert(75, receiveM);
            send.writeUTF(dataSend2);
            send.flush();

            // Receiving third 75 bytes data from p1 
            // and sending third 100 bytes to p2
            receiveM = receive.readUTF();
            sb.insert(150, receiveM);
            send.writeUTF(dataSend3);
            send.flush();

            // Receiving fourth 75 bytes data from p1 
            receiveM = receive.readUTF();
            sb.insert(225, receiveM);
            receive.close();

            temp = sb;
            System.out.println("Transmission Completed!");

            // Terminate the socket connection
            s.close();
            System.out.println("---Socket terminated---");

            // Close the server service
            ss.close();
            System.out.println("-----Server closed-----");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getFileInfo("f2.txt");
        readFile("f2.txt");
        dataProcess(data);
        server(5000);
        writeFile("f2.txt");
    }
}
