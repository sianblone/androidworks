package com.biz.img;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.biz.img.domain.Addresses;
import com.biz.img.utils.InputFieldName;
import com.biz.img.utils.RequestCode;
import com.google.android.material.textfield.TextInputEditText;

public class InputActivity extends AppCompatActivity {

    TextInputEditText txt_name;
    TextInputEditText txt_tel;
    TextInputEditText txt_addr;

    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        txt_name = findViewById(R.id.txt_name);
        txt_tel = findViewById(R.id.txt_tel);
        txt_addr = findViewById(R.id.txt_addr);

        btn_save = findViewById(R.id.btn_save);

        /*
        btn_save를 클릭하면 현재 화면을 닫고
        MainActivity로 txt_name, txt_tel, txt_addr 전달하기
        */
        /*
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */

        btn_save.setOnClickListener( (view)->{

            String strName = txt_name.getText().toString();
            String strTel = txt_tel.getText().toString();
            String strAddr = txt_addr.getText().toString();

            Addresses addrDTO = Addresses.builder()
                    .a_name(strName)
                    .a_tel(strTel)
                    .a_addr(strAddr)
                    .build();

            // 나를 호출한 Activity의 context를 가져와서 값을 리턴하기 위한 준비
            Intent myIntent = getIntent();

            // Spring에서 Model에 변수,값을 설정하는 것과 같은 맥락
            myIntent.putExtra(InputFieldName.TXT_NAME, strName);
            myIntent.putExtra(InputFieldName.TXT_TEL, strTel);
            myIntent.putExtra(InputFieldName.TXT_ADDR, strAddr);

            // putExtra에 VO 객체를 담을 때 오류가 발생하면
            // VO 클래스에 Serializable이 implements 되어있는지 확인
            myIntent.putExtra("ADDR", addrDTO);

            // 현재 Activity를 닫고 위에 putExtra로 설정한 값들을
            // MainActivity로 전달하기
            // 비유하자면 스프링에서 JSP와 렌더링하는 것과 비슷
            setResult(RequestCode.RESULT_SUCCESS, myIntent);
            finish();
        } );
    }


}
