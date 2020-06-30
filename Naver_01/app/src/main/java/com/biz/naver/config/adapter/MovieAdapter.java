package com.biz.naver.config.adapter;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biz.naver.R;
import com.biz.naver.domain.NaverMovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {

    List<NaverMovieItem> movieVOList;

    public MovieAdapter() {
        movieVOList = new ArrayList<>();
    }

    public MovieAdapter(List<NaverMovieItem> movieVOList) {
        this.movieVOList = movieVOList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_view, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieHolder mHolder = (MovieHolder) holder;

        String strTitle = movieVOList.get(position).getTitle();
        strTitle = "<font color=blue>" + strTitle + "</font>";
//        strTitle = "<a href=" + movieVOList.get(position).getLink() + ">"
//                + strTitle + "</a>";
        mHolder.txt_title.setText(this.getHTML(strTitle));
        Linkify.addLinks(mHolder.txt_title, , movieVOList.get(position).getLink());

        String strDirector = movieVOList.get(position).getDirector();
        strDirector = "<b>감독: </b>" + strDirector;
        mHolder.txt_director.setText(this.getHTML(strDirector));

        String strActor = movieVOList.get(position).getActor();
        strActor = "<b>주연 : </b>" + strActor;
        mHolder.txt_actor.setText(this.getHTML(strActor));

        mHolder.txt_link.setText(movieVOList.get(position).getLink());

        try {
            int intRating = (int)(Float.valueOf(movieVOList.get(position).getUserRating()) / 2);
            String strRating = "";
            for(int i = 0; i < intRating ; i++) {
                strRating += "★";
            }

            strRating = "<b>평점 : </b><font color=blue>" + strRating + "</font>";
            mHolder.txt_rating.setText(this.getHTML(strRating));

        } catch (Exception e) {

        }

        String imageLink = movieVOList.get(position).getImage();
        if(!imageLink.isEmpty()) {
            Picasso.get().load(imageLink).into(mHolder.m_image);
            // imageLink가 비어있지 않다면 링크 이미지를 다운받아서
            // mHolder.m_image의 ImageView에 표시하기

            // Glide.with(mHolder.itemView.getContext()).load(imageLink).into(mHolder.m_image);
            // Glide도 Picasso와 사용방법이 거의 비슷하다. 이미지로딩 라이브러리 둘 중 택1
        }

    }

    // 문자열에 HTML Tag가 들어있는 경우 해당 문자열에 HTML 효과를 적용하기 위해 변환 method 생성
    private Spanned getHTML(String strText) {
        Spanned spText;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 누가 버전 이상일 경우
            spText = Html.fromHtml(strText, Html.FROM_HTML_MODE_LEGACY);
        } else {
            // 그 미만 버전일 경우
            spText = Html.fromHtml(strText);
        }
        return spText;
    }

    @Override
    public int getItemCount() {
        return movieVOList == null ? 0 : movieVOList.size();
    }


    class MovieHolder extends RecyclerView.ViewHolder {

        TextView txt_title;
        TextView txt_director;
        TextView txt_actor;
        TextView txt_rating;
        TextView txt_link;
        ImageView m_image;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.m_item_title);
            txt_director = itemView.findViewById(R.id.m_item_director);
            txt_actor = itemView.findViewById(R.id.m_item_actor);
            txt_rating = itemView.findViewById(R.id.m_item_rating);
            txt_link = itemView.findViewById(R.id.m_item_link);
            m_image = itemView.findViewById(R.id.m_item_image);

        }
    }
}

