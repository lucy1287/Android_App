package com.example.inputoutputapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MultiFileAdapter extends RecyclerView.Adapter<MultiFileAdapter.ViewHolder>{
    private ArrayList<Uri> uriList = null ;
    private ArrayList<String> nameList = null ;
    private Context mContext = null ;

    // 생성자에서 데이터 리스트 객체, Context를 전달받음.
    MultiFileAdapter(ArrayList<Uri> list1, ArrayList<String> list2, Context context) {
        uriList = list1 ;
        nameList = list2;
        mContext = context;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조.
            textView = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public MultiFileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;    // context에서 LayoutInflater 객체를 얻는다.
        View view = inflater.inflate(R.layout.multi_file_item, parent, false) ;	// 리사이클러뷰에 들어갈 아이템뷰의 레이아웃을 inflate.
        MultiFileAdapter.ViewHolder vh = new MultiFileAdapter.ViewHolder(view) ;
        return vh ;
    }

    @Override
    public void onBindViewHolder(MultiFileAdapter.ViewHolder holder, int position) {
        Uri fileUri = uriList.get(position) ;
        String fileName = nameList.get(position);

        Log.d("파일 이름", fileName);
        holder.textView.setText("파일명 : " + fileName + "\n파일 경로 : " + fileUri);
    }

    @Override
    public int getItemCount() {
        return uriList.size() ;
    }

}