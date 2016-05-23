package com.example.darsh.streamer.connections;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.xml.transform.Result;
/**
 * Created by Music007 on 4/13/2016.
 */
//thread
//Class to create connection
    //called by MainScreen.java activity
public class TCPCon extends AsyncTask {
    static Boolean connection_state = Boolean.FALSE;
    static Boolean hand_shake_done = Boolean.FALSE;
    static Socket socket = new Socket();
    static int count=0;
    String file_name;
    static String typeofdata = "IMAGE";
    int z=0;

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            socket = new Socket("192.168.43.207", 2016);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.HSprotocol();
        this.dataToBSend(typeofdata);

        //if(type)*/
        //this.imgaddress();
        return null;
    }
    public  void conect() {
        try {
            socket = new Socket("192.168.173.1", 2016);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.HSprotocol();
    }
    public void dataToBSend(String type) {

       if (hand_shake_done==Boolean.TRUE)
       {
           try {
               Log.d("Networking","Sending what is to b send");
               String out_str = type;
               String in_str = "a";
               int in_int;
               byte[] out_msg = new byte[1024];
               byte[] in_msg = new byte[1024];
               out_msg = out_str.getBytes();
               DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
               DataInputStream DIN = new DataInputStream(socket.getInputStream());
               DOS.write(out_msg);
               in_int = DIN.read(in_msg);
               in_str = new String(in_msg);
               in_str = in_str.substring(0, in_int);
               Log.d("Networking", in_str);
               if (in_str.equals("OK IMAGE")) {
                   Log.d("Networking", "Confermed to send image");
                   //hand_shake_done = Boolean.TRUE;
               }
               else {
                   socket.close();
                   Log.d("Networking", "Hand shake fail Socket closed");
               }
           }
           catch(IOException e){
               e.printStackTrace();
               Log.d("Networking", "Some thing wrong " + e.toString());
           }
       }
        else
       {
           Log.d("Networking","Hand Shake not yet done");
       }
    }
    public void test0fStatic(){
        Log.d("Networking","accessing from Main Activity");
        Log.d("Networking","The value of hand shake protocol is: "+hand_shake_done);
        Log.d("Networking","The value of connection state is: "+connection_state);
    }
    public void HSprotocol() {
        Log.d("Networking","Initiating Hand Shake protocol");
        connection_state = Boolean.TRUE;
        try {
            String out_str = "Nirav";
            String in_str = "a";
            int in_int;
            byte[] out_msg = new byte[1024];
            byte[] in_msg = new byte[1024];
            out_msg = out_str.getBytes();
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DataInputStream DIN = new DataInputStream(socket.getInputStream());
            DOS.write(out_msg);
            in_int = DIN.read(in_msg);
            in_str = new String(in_msg);
            in_str = in_str.substring(0, in_int);
            Log.d("Networking", in_str);
            if (in_str.equals("OK")) {
                Log.d("Networking", "Hand shake protocol Successful");
                hand_shake_done = Boolean.TRUE;
            }
            else {
                socket.close();
                Log.d("Networking", "Hand shake fail Socket closed");
            }
        }
        catch(IOException e){
            e.printStackTrace();
            Log.d("Networking", "Some thing wrong " + e.toString());
        }
    }
    public void endcon() {
        Log.d("Networking", "Ending connection Forcefully");
        if (connection_state==Boolean.TRUE)
        {
            connection_state = Boolean.FALSE;
            try {
                if (typeofdata.equals("IMAGE")) {
                    String stop = "STOP IMAGE";
                    byte[] byt_stop = new byte[1024];
                    byt_stop = stop.getBytes();
                    DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
                    DOS.write(byt_stop);
                    Log.d("Networking", "Sending stop signal");
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.onCancelled();
            Log.d("Networking", "socket closed");
        }
        count =0;
    }
    public void getCnt(int cnt){
        count = cnt;
        Log.d("Networking","number of images selected: "+count);
    }
    public void imgaddress(String fname,int pos) {
        Log.d("Networking","in imgaddress");
        //New code
        file_name = fname;
        try {

            String str_file;
            RandomAccessFile f = new RandomAccessFile(file_name,"rw");
            long file_length = f.length();
            String str_file_length = ""+file_length;
            //byte[] in_msg = new byte[1024];
            byte[] byt_file = new byte[(int)file_length];
            f.read(byt_file);
            //f.readFully(byt_file);
            //byt_file = getB
            //not working
            /*
            File file = null;
            FileInputStream fileInputStream = new FileInputStream(file = new File(file_name));
            byte[] arr = new byte[(int)file.length()];
            fileInputStream.read(arr,0,arr.length);*/

            //byte[] buffer = new

            //byte[] out_file_length = new byte[1024];
            //int temp = 10;
            //out_file_length = str_file_length.getBytes();
            Log.d("Networking", "the length in string is " + str_file_length);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DataInputStream DIS = new DataInputStream(socket.getInputStream());
            //DOS.write(out_file_length);
            //DOS.flush();
            //DOS.close();
            //DOS.wait(10000);
            //DOS.write();
            Log.d("Networking", "Working till DOS");
            /*//DIS.reset();
            //temp = DIS.read(in_msg);
            Log.d("Networking", "Working till DIN");
            String in_str = new String(in_msg);
            if(in_str.equals("SEND"))
            {
                Log.d("Networking", "Send image 1 now");
            }
            //in_str = in_str.substring(0, temp);*/
            //Log.d("Networking", "the received msg is " + in_str);
            //DOS.flush();
            DOS.write(byt_file);
            //DOS.write(byt_file,0,79556);
            //DOS.write(byt_file);
            //DOS.write(byt_file);


        } catch (FileNotFoundException e) {
            Log.d("Networking","file not found error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        //old working code
        /*
        file_name = fname;
        byte[] out_file_name = new byte[1024];
        out_file_name = file_name.getBytes();

        try {
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.write(out_file_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Networking","The address of the file selected is : "+file_name+"pos: "+pos);
        */
        //old working code ends
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("Networking", "in onPost");

        if(isCancelled()==true) {
            if (connection_state == Boolean.TRUE) {
                /*try {

                    socket.close();
                    Log.d("Networking", "Connection successful closed in TCPCon");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                Log.d("Networking", "Connection not yet established");*/
            }
        }
        connection_state = Boolean.FALSE;
        //Toast.makeText(ImageSlider.class, "", Toast.LENGTH_SHORT).show();
    }
    /*@Override
    protected void onCancelled()
    {
        Log.d("Networking", "in onCacelled");
        if(connection_state==Boolean.TRUE) {
            try {
                connection_state = Boolean.FALSE;
                socket.close();
                Log.d("Networking", "Connection successful closed in TCPCon");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            Log.d("Networking", "Connection not yet established");
        return;
    }*/
}