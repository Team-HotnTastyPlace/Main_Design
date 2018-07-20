package com.example.do_11.Doctorsfab_Android.Socket;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.do_11.Doctorsfab_Android.TabsActivity;
import com.example.do_11.R;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;



public class SocketActivity_list extends ListActivity {

    private String tag = "로그 ; SocketActivity:";

    EditText input01;
    EditText input02;
    EditText editText2;
    EditText editText3;
    TextView textView1;
    String textmsg1;
    String textmag2;
    Spinner spinner;
//    String testmsg_list = "주소리스트test1,주소리스트test2,주소리스트test3,주소리스트test4,주소리스트test5";

    //리스트 추가부분

    public static ArrayList<String> listItems = new ArrayList<String>();

    //리스트의 데이터를 다루는 어댑터 선언
    ArrayAdapter<String> adapter;

    //버튼이 몇번 클릭됐는지를 저장하는 변수
    int clickCounter = 0;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String selectedvalue = (String) getListAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), "위치:" + (position + 1) + "내 용:" + selectedvalue + "클릭 됨", Toast.LENGTH_SHORT).show();


        TabsActivity.tabHost.setCurrentTab(3);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket3);

        input01 = findViewById(R.id.input01);
        input02 = findViewById(R.id.input02);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        //리스트 추가하기

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        //잘라서 리스트로

//        String[] arry1 = testmsg_list.split(",");

//        for (int i = 0; i < arry1.length; i++) {
//            listItems.add(arry1[i]);
//            adapter.notifyDataSetChanged();
//        }

        //etc
        Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText3.setText((CharSequence) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        editText3.setSelection(editText3.getText().length());

//Connect 버튼 주석
        //
        // 버튼 이벤트 처리 Connect
//        Button button01 = (Button) findViewById(R.id.button01);
//        button01.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                Log.d(tag,"connect버튼");
//
//                String addr = input01.getText().toString().trim();
//                String port = input02.getText().toString().trim();
//
//               // textView1.setText(input01.getText().toString() + " 연결:");
//                ConnectThread thread = new ConnectThread(addr, "1", port);
//                thread.start();
//            }
//        });
    }


    /**
     * 소켓 연결할 스레드 정의
     */

    class ConnectThread extends Thread {
        String hostname;
        String port;
       // private BufferedInputStream bis;

        TextView textView2 = (TextView) findViewById(R.id.textView);

        public ConnectThread(String addr, String chatText, String port1) {
            hostname = addr;
            port = port1;
        }

        public void run() {
            try {

                Log.d(tag,"쓰레드 내부");

                Socket sock = new Socket(hostname, Integer.parseInt(port));

                Log.d(tag,"쓰레드 소켓연결됨");

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());

                Log.d(tag,"쓰레드 내부2");

    //         outstream.writeObject(editText2.getText().toString());

               outstream.writeObject("FFFE,35,R,01,00,00,FFFF");

               Log.d(tag,"쓰레드 내부3");

               outstream.flush();

         //       bis = new BufferedInputStream(sock.getInputStream());


                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());

                Log.d(tag,"쓰레드 내부4");

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
