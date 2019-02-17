package com.example.myapplication.mylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.room.model.MyList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.Holder> {

    private final int COLUMN_PER_ROW = 2;
    private ArrayList<MyList> mMyLists;
    private MylistContract.Presenter presenter;
    RecyclerView recyclerView;

    public MyListAdapter(ArrayList<MyList> mMyLists, MylistContract.Presenter presenter,RecyclerView recyclerView) {
        this.mMyLists = mMyLists;
        this.presenter = presenter;
        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(),COLUMN_PER_ROW);

        recyclerView.setLayoutManager(layoutManager);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mylist,viewGroup,false);

        ViewGroup.LayoutParams params = viewGroup.getLayoutParams();
        params.height = params.width;
        viewGroup.setLayoutParams(params);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        if(i<mMyLists.size()){
            holder.setViewTitle(mMyLists.get(i));
        }else {
            holder.setViewAdd();
        }
    }

    @Override
    public int getItemCount() {
        return mMyLists.size()+1;
    }


    class Holder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgAdd)
        ImageView imgAdd;
        @BindView(R.id.txtvTitle)
        TextView txtvTitle;
        @BindView(R.id.txtvAmount)
        TextView txtvAmount;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setFocusable(true);
        }
        private void setClick(){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imgAdd.getVisibility() == View.VISIBLE){

                    }else{

                    }
                }
            });
        }
        public void setViewTitle(MyList mylist){

            if(txtvTitle.getVisibility() == View.GONE){
                txtvTitle.setVisibility(View.VISIBLE);
                txtvAmount.setVisibility(View.VISIBLE);
            }
            if(imgAdd.getVisibility() == View.VISIBLE){
                imgAdd.setVisibility(View.GONE);
            }
            txtvAmount.setText(""+mylist.cntItem);
            txtvTitle.setText(mylist.name);
        }
        public void setViewAdd(){
            if(txtvTitle.getVisibility() == View.VISIBLE){
                txtvTitle.setVisibility(View.GONE);
                txtvAmount.setVisibility(View.GONE);
            }
            if(imgAdd.getVisibility() == View.GONE){
                imgAdd.setVisibility(View.VISIBLE);
            }
        }
    }
}
