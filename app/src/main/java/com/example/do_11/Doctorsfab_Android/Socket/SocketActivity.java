package com.example.do_11.Doctorsfab_Android.Socket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.do_11.R;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 안드로이드에서 소켓 클라이언트로 연결하는 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 */
public class SocketActivity extends AppCompatActivity {

    EditText input01;
    EditText input02;
    EditText editText2;
    TextView textView1;
    String textmsg1;
    String textmag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        input01 = (EditText) findViewById(R.id.input01);
        input02 = (EditText) findViewById(R.id.input02);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView1 = (TextView) findViewById(R.id.textView);
        textView1.setMovementMethod(new ScrollingMovementMethod());


        // 버튼 이벤트 처리 Connect
        Button button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String addr = input01.getText().toString().trim();
                String port = input02.getText().toString().trim();
                textView1.setText(input01.getText().toString() + " 연결:");
                ConnectThread thread = new ConnectThread(addr, "1", port);
                thread.start();
            }
        });

        // 버튼 이벤트 처리 Send
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String addr = input01.getText().toString().trim();
                String chatText = editText2.getText().toString().trim();
                String port = input02.getText().toString().trim();

               //나중에 수정하기
                textView1.setText(textView1.getText().toString() + editText2.getText().toString() + "\n");
                textView1.setText(textView1.getText().toString() + textmag2 + "\n");

                ConnectThread thread = new ConnectThread(addr, chatText, port);
                thread.start();

            }
        });
    }

    /**
     * 소켓 연결할 스레드 정의
     */
    class ConnectThread extends Thread {
        String hostname;
        String port;
        TextView textView2 = (TextView) findViewById(R.id.textView);

        public ConnectThread(String addr, String chatText, String port1) {
            hostname = addr;
            port = port1;
        }

        public void run() {
            try {
                Socket sock = new Socket(hostname, Integer.parseInt(port));
                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(editText2.getText().toString());
                outstream.flush();

                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                String obj = (String) instream.readObject();


                //textView1.getText().toString()+
                Log.d("MainActivity", "서버에서 받은 메시지 : " + obj);
                textmag2 = obj;
                //sock.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
