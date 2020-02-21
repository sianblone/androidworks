package com.biz.img.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
/*
    implements Serializable
    Spring web에서 보내는 데이터는 모두 문자열로, CharSequence 방식으로 행렬로 전달된다
    ModelAndAttribute로 받을 때는 Serializable로 인터페이스를 implements 한 VO(DTO)로 받아야 한다
    (Spring web에서는 선택사항)

    안드로이드에서는 반드시 VO 클래스에 implements Serializable을 해줘야
    Intent간 객체에 값을 담아서 전달할 수 있다
 */
public class Addresses implements Serializable {

    private String a_name;
    private String a_tel;
    private String a_addr;

}
