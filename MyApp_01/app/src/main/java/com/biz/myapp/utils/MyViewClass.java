package com.biz.myapp.utils;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.biz.myapp.R;
import com.google.android.material.snackbar.Snackbar;

/*
    View 클래스

    안드로이드에서 눈에 보이는 모든 것(Layout, Button, Textview, TextEdit)은
    모두 View 클래스를 상속받아서 만들어진 component 들이다
    어떤 이벤트나 액션 등을 지정할 때 기존의 클래스나 인터페이스를 상속 또는 implement 해야 하는데
    그 때 자기 자신의 클래스를 사용하지 않고 View 클래스의 요소들을 상속받아서 클래스를 작성하는 것이
    일반적인 패턴이다
*/
public class MyViewClass implements Button.OnClickListener {
/*
    현재 onClick 메소드는 btn1 Button을 클릭했을 때 호출되는 method인데
    btn1을 클릭하면 클릭된 버튼의 모든 요소가 View인 변수 v(객체)에 담겨 onClick() method로 전달된다
 */
    @Override
    public void onClick(View v) {
        /*
        이벤트가 발생하면 onClick() 메소드가 실행(호출)될 것이고
        누가 호출했는지 알고 싶을 때 v.getId() 메소드를 사용하면 호출한 view(component)의 id값을 얻어올 수 있다
         */
        String msg = "반갑습니다";
        if(v.getId() == R.id.btn1) {
            msg += "\n나는 버튼입니다";
        } else if(v.getId() == R.id.txt1) {
            /*
            이벤트가 발생한 view(component)로부터 어떤 값을 얻어오고자 하면
            해당 view를 형 변환(type cast)하여 객체를 만들고
            만든 객체에서 각 view의 고유한 메소드를 호출하면 된다
             */
            TextView t = (TextView)v;
            msg += "\n" + t.getText();
        } else if(v.getId() == R.id.txt2) {
            TextView t = (TextView)v;
            msg += "\n" + t.getText();
        }
        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }
}
