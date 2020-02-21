package com.biz.img;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.biz.img.domain.Addresses;
import com.biz.img.utils.InputFieldName;
import com.biz.img.utils.RequestCode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] strReq = new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, "갤러리 접근 권한이 필요합니다"
            };
            ActivityCompat.requestPermissions(MainActivity.this, strReq, 1000);
        }
        imageView = findViewById(R.id.image_view);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent : 안드로이드에서 보여지는 화면(page)
                다른 화면을 보이고자 할 때는 Intent를 만들고 startActivity() 메소드를 이용하여
                intent를 열어준다
                */
                /*
                // 새로운 창을 열어 값을 입력받고, 다시 되돌려받기 위한 코드
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                // startActivity(intent);

                // requestCode : 열리는 Activity와 열려있는 Activity가 서로 데이터를 교환할 때 사용할 ID 값
                startActivityForResult(intent, RequestCode.REQUEST_INPUT_CODE);
                */
                // 폰에 저장된 이미지를 불러와서 content.xml의 ImageView에 표시하려면
                // 폰에 저장된 이미지 정보를 보기 위한 시스템 내장 Intent 호출하기
                Intent imgIntent = new Intent(Intent.ACTION_PICK);

                // 지금부터 이미지 저장 폴더에 접근하겠다는 설정
                imgIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                // 이미지를 저장하는 공간: 내장메모리, 외장 SD 카드 전부 접근가능 하도록 설정
                imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // 이미지 보기가 열렸을 때 이미지를 클릭하면
                // 해당 이미지 정보를 return받기 위해 Intent 열기
                startActivityForResult(imgIntent, RequestCode.REQUEST_IMAGE_CODE);

            }
        });
    }// end onCreate()

    // 현재 Activity에서 새로운 Activity를 열었을 때
    // 새로운 Activity가 어떤 값을 전달하면 그 값을 수신할 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RequestCode.REQUEST_IMAGE_CODE) {
            // 이미지 갤러리가 보내는 데이터를 받아서 ImageView에 이미지 표시
            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(is);
                is.close();
                imageView.setImageBitmap(img);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if(requestCode == RequestCode.REQUEST_INPUT_CODE) {

            if(resultCode == RequestCode.RESULT_SUCCESS) {
                // 1번 방식
                String strName = data.getStringExtra(InputFieldName.TXT_NAME);
                String strTel = data.getStringExtra(InputFieldName.TXT_TEL);
                String strAddr = data.getStringExtra(InputFieldName.TXT_ADDR);

                // 2번 방식
                Addresses addrDTO = (Addresses) data.getSerializableExtra("ADDR");
                String dtoName = addrDTO.getA_name();
                String dtoTel = addrDTO.getA_tel();
                String dtoAddr = addrDTO.getA_addr();

                String msg = String.format("이름 : %s\n전화번호: %s\n주소: %s", strName, strTel, strAddr);

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            } else if(resultCode == RequestCode.RESULT_ERROR) {
                Toast.makeText(MainActivity.this, "오류 발생", Toast.LENGTH_LONG).show();
            }
        }
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
}
