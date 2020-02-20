package com.biz.myapp;

import android.os.Bundle;

import com.biz.myapp.utils.MyViewClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/*
    두번째로 많이 사용하는 안드로이드 이벤트 핸들러 방식
    Activity 자체를 이벤트 핸들러로 선언하기
    View.On** 클래스를 implements 해주고
    on**() 메소드 구현하는 방식

    사용할 때는 view.setOn**() 메소드 안에 매개변수로 this라는 키워드를 넣어준다

 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txt11;
    TextView txt22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*txt11 = findViewById(R.id.txt1);
        txt22 = findViewById(R.id.txt2);

        TextView txt1 = findViewById(R.id.txt1);
        TextView txt2 = findViewById(R.id.txt2);
        Button btn1 = findViewById(R.id.btn1);

        MyViewClass mvc = new MyViewClass();

//        txt1.setOnClickListener(mvc);
//        txt2.setOnClickListener(new MyViewClass());
        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);

        btn1.setOnClickListener(this);*/

        /*
        이벤트 핸들링 첫 번째 방식
        익명(무명) 클래스를 생성하여 자체적으로 이벤트 핸들링하기
         */
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    자체에서 onClick() 메소드를 구현하여 이벤트를 핸들링하는 이유
    이벤트를 처리해야 할 view(comp)들이 있는데
    이벤트 처리 코드가 유사한 경우 한번만 이벤트 처리 코드를 작성하고 view들이 공유해서 사용하기 위함
    v.getId() 메소드를 이용해서 누가 호출했는지 구분해서 코드를 진행하면 된다
     */
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.txt2) {
            txt11.setText(txt22.getText());
        } else {
            txt11.setText("txt11");
        }
    }
}
