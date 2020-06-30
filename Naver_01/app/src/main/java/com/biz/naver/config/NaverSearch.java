package com.biz.naver.config;

import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.biz.naver.config.adapter.MovieAdapter;
import com.biz.naver.domain.NaverMovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

// 네이버 API를 비동기 방식으로 호출하여 데이터 가져오기
// 동기, 비동기, 단일쓰레드, 멀티쓰레드
// 가장 안좋은 성능 : 단일쓰레드 동기 방식
// 가장 좋은 성능 : 멀티쓰레드 비동기 방식
public class NaverSearch extends AsyncTask<Integer, Integer, Void> {

    private final String NAVER_MOVIE_URL = "https://openapi.naver.com/v1/search/movie.json";
    private String strSearch;
    private List<NaverMovieItem> mList;
    private RecyclerView recyclerView;

    public NaverSearch() {

    }

    // 검색어와 RecyclerView를 전달받아서 검색을 수행하고 RecyclerView에 보이기
    public NaverSearch(String strSearch, RecyclerView recyclerView) {
        this.strSearch = strSearch;
        this.recyclerView = recyclerView;
    }

    public List<NaverMovieItem> getmList() {
        return mList;
    }

    /*
                매개변수에 변수타입... 변수명 형식으로 지정하면
                매개변수가 몇 개인지 관계없이 어떤 부분이라도 이 메소드를 호출할 수 있다
                매개변수의 수가 정해지지 않은 호출방식
                doInBackground(1,2,3,4,5,6,7)
                변수를 배열로 넘길 때 배열의 크기에 상관없이 넘기고 싶을때 사용하는 Java 1.8 이상에서 사용 가능한 코드
                Integer[] integers = new Integer[7]
                 */
    @Override
    protected Void doInBackground(Integer... integers) {
        this.naver_search();
        return null;
    }

    /*
    onPostExecute
    안드로이드에서 doInBackground() 메소드가 naver_search() 메소드를 호출하여
    백그라운드에서 실행하고, 처리가 완료되면 완료 event를 받을 메소드
    => RecyclerView에 데이터 표현
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MovieAdapter movieAdapter = new MovieAdapter(mList);
        recyclerView.setAdapter(movieAdapter);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void naver_search() {

        try {
            String api_url_query = NAVER_MOVIE_URL
                    + "?query=" + URLEncoder.encode(strSearch, "UTF-8")
                    + "&display=20";


            URL url = new URL(api_url_query);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-Naver-Client-Id", NaverSecure.NAVER_ID);
            httpURLConnection.setRequestProperty("X-Naver-Client-Secret", NaverSecure.NAVER_SEC);

            int resCode = httpURLConnection.getResponseCode();

            BufferedReader bReader;
            if(resCode == 200) {
                bReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            } else {
                bReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
            }

            String resString = "";
            String reader;
            while(true) {
                reader = bReader.readLine();
                if(reader == null) break;
                resString += reader;
            }

            JSONObject resJson = new JSONObject(resString);

            JSONArray resItems = resJson.getJSONArray("items");
            mList = new ArrayList();
            // Java 1.8 이상에서는 List<Type>이 설정되면 ArrayList 제네릭 설정 안해도 됨
            for(int i = 0; i < resItems.length(); i++) {
                JSONObject item = resItems.getJSONObject(i);//JSONArray 배열의 0번부터 차례대로 가져오기

                NaverMovieItem mVO = NaverMovieItem.builder()
                        .title(item.getString("title"))
                        .director(item.getString("director"))
                        .actor(item.getString("actor"))
                        .link(item.getString("link"))
                        .userRating(item.getString("userRating"))
                        .image(item.getString("image"))
                        .build();

                mList.add(mVO);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }
}
