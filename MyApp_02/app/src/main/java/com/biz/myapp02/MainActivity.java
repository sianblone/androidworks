package com.biz.myapp02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.biz.myapp02.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        registerForContextMenu(fab);
        fab.setOnClickListener(this);

        txt1 = findViewById(R.id.txt1);
        registerForContextMenu(txt1);
        txt1.setOnClickListener(this);

    } // onCreate end

    // onCreateOptionsMenu
    // menu 리소스를 현재 activity의 Appbar에 표시하기 위해 메뉴를 설정하는 method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Inflater : 리소스를 가져와서 코드에 부착하여 사용할 수 있도록 하기

        return true;
        // 메뉴를 Activity에 부착할 때 제일 먼저 수정할 코드, true로 해줘야 메뉴가 나타난다
    }

    // option 메뉴 중 터치된 메뉴의 id값 추출하기
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int m_id = item.getItemId();// 선택된 메뉴의 id값 가져오기
        if(m_id == R.id.m_login) {// 만약 id값이 R.id.m_login과 같다면

            // Intent는 현재 Activity 위에 다른 Activity를 띄우기 위한 클래스
            // 생성자에 현재의 Activity와 새로 열릴 클래스를 매개변수로 전달한다
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            return true;

        } else if(m_id == R.id.m_settings) {
            Intent setIntent = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(setIntent);
            return true;
        }

        return true;
    }

    /*
        화면에 표시된 View에 ContextMenu를 설정하기 위한 method
        설정된 view에 따라 반응형 ContextMenu 적용 가능

        ContextMenu 설정 순서
        1. menu.xml 리소스 작성
        2. Activity에서 onCreateContextMenu() 메소드 불러와서 menu.xml 리소스를 Inflater() 설정
        3. onCreate() 메소드에서 contextMenu를 적용할 view에 registerForContext()로 등록하기
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int id = v.getId();
        if(id == R.id.txt1) {
            getMenuInflater().inflate(R.menu.menu_hello_context, menu);
        } else if(id == R.id.fab) {
            getMenuInflater().inflate(R.menu.menu_fab_context, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        // ContextMenu.ContextMenuInfo m_info = item.getMenuInfo();
        int m_id = item.getItemId();
        if(m_id == R.id.m_fab_con_settings) {
            Intent setIntent = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(setIntent);
            return true;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        int v_id = v.getId();
        String msg = "반갑습니다";
        if(v_id == R.id.txt1) {
            msg += "\n나는 TextView입니다";
        } else if(v_id == R.id.fab) {
            msg += "\n나는 플로팅 액션 버튼입니다";
        }

        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }
}
