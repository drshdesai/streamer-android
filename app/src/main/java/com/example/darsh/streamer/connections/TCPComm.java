package com.example.darsh.streamer.connections;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Music007 on 4/13/2016.
 */
//thread
//class for communication
//called by ImageSlider.java class activity
public class TCPComm extends AsyncTask {
    int count=0;
    @Override
    protected Object doInBackground(Object[] params) {

        Log.d("Networking","communication Successful in tcpComm");
        try {

            String str = "u have selected "+count+" images";

            byte[] msg = new byte[1024];
            msg = str.getBytes();
            Socket socket = new Socket("192.168.173.1",2016);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.write(msg);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Networking", "Some thing wrong");
        }
        return null;
    }
    public void getcnt(int cnt){
        count = cnt;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //Toast.makeText(ImageSlider.class, "", Toast.LENGTH_SHORT).show();
    }
}