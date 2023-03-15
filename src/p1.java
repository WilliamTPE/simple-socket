import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class p1 {
    static String temp;
    static String receiveM;
    static String data;
    static String dataSend1="", dataSend2="", dataSend3="", dataSend4="";

    /**
     * Read text file from directory
     */
    public static void readFile(String fileName){
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
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
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("File size: " + myObj.length() + " byte(s)");
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
            myWriter.write(temp);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Separate 300 bytes data into 4 parts
     * Each part is 75 bytes
     * @param data text file data
     */
    private static void dataProcess(String data){
        for (int i = 0; i < 75; i++){
            dataSend1 = dataSend1 + data.charAt(i);
        }
        for (int i = 75; i < 150; i++){
            dataSend2 = dataSend2 + data.charAt(i);
        }
        for (int i = 150; i < 225; i++){
            dataSend3 = dataSend3 + data.charAt(i);
        }
        for (int i = 225; i < 300; i++){
            dataSend4 = dataSend4 + data.charAt(i);
        }
    }

    /**
     * Socket main program - client
     * @param port the Socket port
     */
    public static void client(int port) {
        try {
            // Create a new socket client object
            Socket s = new Socket("dc30.utdallas.edu", port);
            System.out.println("----Initiate Socket-----");
            System.out.println("Connected to " + s.getInetAddress());
            System.out.println("Port: " + s.getPort());

            // Create an object for receiving data
            DataInputStream receive = new DataInputStream(s.getInputStream());

            // Create an object for sending data
            DataOutputStream send = new DataOutputStream(s.getOutputStream());

            // Assign the text file data to temporary area
            temp = data;

            // Sending first 75 bytes data to p2 
            // and receiving first 100 bytes from p1
            send.writeUTF(dataSend1);
            send.flush();
            receiveM = receive.readUTF();
            temp = temp + receiveM;

            // Sending second 75 bytes data to p2 
            // and receiving second 100 bytes from p1
            send.writeUTF(dataSend2);
            send.flush();
            receiveM = receive.readUTF();
            //sb.insert(100, receiveM);
            temp = temp + receiveM;

            // Sending third 75 bytes data to p2 
            // and receiving third 100 bytes from p1
            send.writeUTF(dataSend3);
            send.flush();
            receiveM = receive.readUTF();
            temp = temp + receiveM;
        
            // Sending fourth 75 bytes data to p2 
            send.writeUTF(dataSend4);
            send.flush();
            send.close();

            System.out.println("Transmission Completed!");

            s.close();
            System.out.println("---Socket terminated---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getFileInfo("f1.txt");
        readFile("f1.txt");
        dataProcess(data);
        client(5000);
        writeFile("f1.txt");
    }
}
