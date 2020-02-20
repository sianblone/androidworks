package com.biz.memo.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.biz.memo.db.MemoDataBase;
import com.biz.memo.domain.MemoVO;

import java.util.List;

// DB에 접근할 때 사용할 서비스 클래스
public class MemoRepository {

   private MemoDao memoDao;

    public MemoRepository(Application application) {
        MemoDataBase db = MemoDataBase.getInstance(application);
        memoDao = db.getMemoDao();
    }

    public LiveData<List<MemoVO>> selectAll() {
        return memoDao.selectAll();
    }

    /* thread로 insert 실행 */
    public void insert(MemoVO memoVO) {
        /*
        MemoDataBase.dbWriterThread.execute(new Runnable() {
            @Override
            public void run() {
                memoDao.save(memoVO);
            }
        });
        */

        MemoDataBase.dbWriterThread.execute(()->memoDao.save(memoVO));

    }

    public void delete(MemoVO memoVO) {
        MemoDataBase.dbWriterThread.execute( () -> memoDao.delete(memoVO) );
    }
}
