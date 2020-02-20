package com.biz.memo.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biz.memo.R;
import com.biz.memo.domain.MemoVO;

import org.w3c.dom.Text;

import java.lang.reflect.Modifier;
import java.util.List;

public class MemoViewAdapter extends RecyclerView.Adapter {

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClicked(MemoVO memoVO);
    }

    private Context context = null;
    private List<MemoVO> memoList = null;
    private LayoutInflater layoutInflater = null;

    // 삭제버튼 이벤트를 저장할 객체 변수를 선언하고
    private OnDeleteButtonClickListener deleteBtnClick;

    // 삭제버튼 이벤트의 본체를 외부로부터 주입(전달)받을 수 있는 setter 선언
    public void setDeleteBtnClick(OnDeleteButtonClickListener event) {
        this.deleteBtnClick = event;
    }

    /*
        MainActivity에서 MemoViewAdapter를 만들 때
        Context와 memoList를 주입할 생성자
     */
    public MemoViewAdapter(Context context, List<MemoVO> memoList) {
        // 만약 context, list를 매개변수로 MemoViewAdapter를 생성하면
        // context 변수 값은 context 생성자로 넘겨 그곳에서 layout
        this(context);
        this.memoList = memoList;
    }

    // context 생성자
    public MemoViewAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setMemoList(List<MemoVO> memoList) {
        // 1. 외부에서 list를 주입받고
        // 2. recyclerview에 세팅
        this.memoList = memoList;

        // recyclerview에 알람 보내기
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate : xml 파일을 가져와서 view 객체로 생성하기
        // View view = LayoutInflater.from(context).inflate(R.layout.memo_item, parent, false);
        View view = layoutInflater.inflate(R.layout.memo_item, parent, false);

        MemoHolder holder = new MemoHolder(view);

        return holder;
    }

    /*
        memoList의 개수만큼 반복문(for)으로 호출되는 메소드
        반복문이 호출되면서 몇 번째 데이터인지 position 변수에 주입
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /*
            RecyclerView.ViewHolder를 MemoHolder로 형변환하여
            MemoHolder에 직접 접근할 수 있도록 하기
         */
        MemoHolder memoHolder = (MemoHolder) holder;

        /*
            memoList의 각 아이템 요소를 한 개씩 읽어서
            TextView setText() method를 이용해 문자열 입력하기
         */
        memoHolder.item_date.setText(memoList.get(position).getM_date());
        memoHolder.item_time.setText(memoList.get(position).getM_time());
        memoHolder.item_text.setText(memoList.get(position).getM_text());
        /*
        memoHolder.item_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        /*
        화면에 보이는 아이템만 삭제하는 이벤트 코드
        실제 DB에 데이터를
        DB를 처리해야 한다
        그렇게 하기에는 여러가지 퍼포먼스에서 문제가 발생할 수 있다
        이벤트를 MainActivity로 옮겨서 거기에서 설정한 후 가져와서 처리를 해줘야한다
         */
        memoHolder.item_btn_delete.setOnClickListener((view)->{
            memoList.remove(position);
            notifyDataSetChanged();
        });

        memoHolder.item_btn_delete.setOnClickListener(v->deleteBtnClick.onDeleteButtonClicked(memoList.get(position)));

    }

    @Override
    public int getItemCount() {
        return this.memoList != null ? this.memoList.size() : 0;
    }

    /*
        memo_item.xml에 설정한 여러가지 view들을 사용할 수 있도록 초기화하는 과정
     */
    class MemoHolder extends RecyclerView.ViewHolder {

        public TextView item_time;
        public TextView item_date;
        public TextView item_text;
        public Button item_btn_delete;

        public MemoHolder(@NonNull View itemView) {
            super(itemView);
            item_date = itemView.findViewById(R.id.item_date);
            item_time = itemView.findViewById(R.id.item_time);
            item_text = itemView.findViewById(R.id.item_text);
            item_btn_delete = itemView.findViewById(R.id.item_delete);
        }

    }
}
