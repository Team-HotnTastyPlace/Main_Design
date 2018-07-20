package com.example.do_11.Doctorsfab_Android.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.do_11.Doctorsfab_Android.TabsActivity;
import com.example.do_11.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



/* Gateway용 -Main
 *
 *   @20180706
 *
 *  192.168.55.1
 *  192.168.1.100
 *
 *
 * */

public class Socket_Main extends AppCompatActivity {


    final private static String tag = "Tag:";

    public EditText input01 = null;
    public EditText input02 = null;
    public EditText editText2 = null;
    public TextView textView1 = null;
    public Button button01 = null;
    public Button button02 = null;
    public Button btnReceived = null;
    public Spinner spinner = null;

    public Socket socket = null;

    String address = null;
    String port = null;

    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    public BufferedReader br;


    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_main);

        input01 = findViewById(R.id.input01);
        input02 = findViewById(R.id.input02);
        editText2 = findViewById(R.id.editText2);
        textView1 = findViewById(R.id.textView);
        textView1.setMovementMethod(new ScrollingMovementMethod());


        button01 = findViewById(R.id.button01);
        button02 = findViewById(R.id.button2);

        Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText2.setText((CharSequence) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        char CR = (char) 0x0D;
        char LF = (char) 0x0A;
        String CRLF = "" + CR + LF;
        String b = "b";

//spinner2 주석
//        final String[] Example = new String[]{"test1", b, CRLF};
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Example);
//
//        Spinner spinner2 = new Spinner(this);
//        spinner2 = findViewById(R.id.spinner2);
//        spinner2.setAdapter(adapter);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                editText2.setText((CharSequence) parent.getItemAtPosition(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        button01.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                address = input01.getText().toString().trim();
                port = input02.getText().toString().trim();

                textView1.append("연 결 클릭:");

                Log.d(tag, String.valueOf(oos));


                SetSocket setSocket = new SetSocket(address, port);
                setSocket.start();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(tag, String.valueOf(oos));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendMsg sendMsg = new SendMsg(oos);
                sendMsg.start();

            }
        });

//        btnReceived.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ReceivedMsg receivedMsg = new ReceivedMsg(ois, br);
//                receivedMsg.start();
//            }
//        });


    }

    class SetSocket extends Thread {

        String address = null;
        String port = null;
        String fstMsg;

        SetSocket(String address, String port) {
            this.address = address;
            this.port = port;
        }

        public void run() {

            try {
                Log.d(tag, "!!!!!!");
                socket = new Socket(address, Integer.parseInt(port));

                Log.d(tag, "socketAddress:" + socket.getInetAddress());

                boolean isConnected = socket.isConnected();
                boolean isClosed = socket.isClosed();

                Log.d(tag, "isConnected : " + isConnected);
                Log.d(tag, "isClosed : " + isClosed);
//              oos.flush();

                oos = new ObjectOutputStream(socket.getOutputStream());

                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                fstMsg = br.readLine();
                Log.d(tag, ":" + fstMsg);
                handler.post(new Runnable() {

                    @Override
                    public void run() {

                        textView1.append("\n 소켓연결중:");

                        if (fstMsg != null)

                            textView1.append("\n" + fstMsg);
                        textView1.append("\n 소켓연결확인end.");
                    }
                });


                ReceivedMsg receivedMsg = new ReceivedMsg(ois, br);
                receivedMsg.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SendMsg extends Thread {

        String MessageSended;
        ObjectOutputStream oos = null;

        SendMsg(ObjectOutputStream oos) {
            this.oos = oos;
        }

        public void run() {

            MessageSended = editText2.getText().toString() + " ";

            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView1.append("\n" + "발신 :" + MessageSended);
                }
            });

            try {
//끝에 공백 넣기
//                char CR =(char)0x0D;
//                char LF =(char)0x0A;
//                String CRLF = ""+CR+LF;
//                MessageSended = MessageSended +CRLF;

                oos.writeObject(MessageSended);
            } catch (IOException e) {
                e.printStackTrace();
            }
            interrupt();
        }

    }

    class ReceivedMsg extends Thread {

        String MessageReceived;
        BufferedReader br;
        ObjectInputStream ois1;
        BufferedReader br2;
        Boolean APinfo = false;
        ReceivedMsg(ObjectInputStream ois, BufferedReader br) {
            this.ois1 = ois;
            this.br2 = br;
        }

        public void run() {

            while (true) {

                Log.d(tag, "리시브중임..");

                try {
                    Log.d(tag, "리시브리드라인 전..");

                    Thread.sleep(200);

                    MessageReceived = br2.readLine();

                    Log.d(tag, "리시브리드라인 ..");
                    if (MessageReceived != null)
                        Log.d(tag, MessageReceived);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    //StreamCorruptedException 발생
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView1.append("\n" + "수신 :" + MessageReceived);
                        if(MessageReceived.contains("AP Info!")){
                            APinfo = true;
                        }
                        if(APinfo == true){
                            SocketActivity_list.listItems.add(MessageReceived);
                            Log.d(tag,"추가 됨");
                        }

                        if(MessageReceived.contains("AP Found:")){
                            TabsActivity.tabHost.setCurrentTab(3);
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
