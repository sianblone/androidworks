package com.biz.memo.adapter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.biz.memo.domain.MemoVO;
import com.biz.memo.repository.MemoRepository;

import java.util.List;

// 안드로이드에서의 ViewModel
// DB와 연동하여 화면에 데이터를 보일때 직접 DB로부터 데이터를 가져와서 보이지 않고
// 또 하나의 중간 매개체를 통해 처리를 수행하기 위해 사용하는 클래스
public class MemoViewModel extends AndroidViewModel {

    private MemoRepository memoRepository;
    private LiveData<List<MemoVO>> memoList;

    public MemoViewModel(@NonNull Application application) {
        super(application);
        this.memoRepository = new MemoRepository(application);
        this.memoList = memoRepository.selectAll();
    }

    public LiveData<List<MemoVO>> selectAll() {
        return this.memoList;
    }

    public void insert(MemoVO memoVO) {
        memoRepository.insert(memoVO);
    }

    public void delete(MemoVO memoVO) {
        memoRepository.delete(memoVO);
    }
}
