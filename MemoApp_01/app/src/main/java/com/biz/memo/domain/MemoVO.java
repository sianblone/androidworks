package com.biz.memo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/*
FTS Ver 4
Full Text Search를 수행하는 방법은 3,4가 있는데
최신 Android Studio(AS)에서는 Fts4를 사용하도록 권장하고 있다
매우 빠른 속도로 전체 텍스트 검색 가능

Fts4는 Room 2.1.0 이상에서 제공되는 기능
 */
@Fts4
@Entity(tableName = "tbl_memo")
public class MemoVO {

    /*
        PK 지정된 숫자형 칼럼에 auto_increment를 부여하는 속성
        SQLite에서 FTS(Full Text Search)라는 패턴을 지원하는 DB 형식
        FTS를 사용하려면 id 칼럼이 반드시 int형이고 칼럼명은 rowid로 설정해주어야 한다
     */

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="rowid")
    private int id;
    @ColumnInfo(name="m_date")
    private String m_date;
    @ColumnInfo(name="m_time")
    private String m_time;
    @ColumnInfo(name="m_text")
    private String m_text;
}
