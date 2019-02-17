package com.example.myapplication.mylist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.room.model.MyList;
import com.example.myapplication.util.Showlog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.Holder> {

    private final int COLUMN_PER_ROW = 2;
    private ArrayList<MyList> mMyLists;
    private MylistContract.Presenter mPresenter;
    RecyclerView mRecyclerView;
    Context mContext;
    EditText editTitleFocus;

    public MyListAdapter(ArrayList<MyList> mMyLists, MylistContract.Presenter presenter,RecyclerView recyclerView) {
        this.mMyLists = mMyLists;
        this.mPresenter = presenter;
        this.mContext = recyclerView.getContext();
        this.mRecyclerView = recyclerView;
        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(),COLUMN_PER_ROW);

        recyclerView.setLayoutManager(layoutManager);

        handleFocus(recyclerView);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mylist,viewGroup,false);

        ViewGroup.LayoutParams params = viewGroup.getLayoutParams();
        params.height = params.width;
        viewGroup.setLayoutParams(params);

        if(i<mMyLists.size()){
            handleFocus(view);
        }

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
        @BindView(R.id.editTitle)
        EditText editTitle;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            //itemView.setFocusable(true);
            setClick();
        }
        private void setClick(){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(imgAdd.getVisibility() == View.VISIBLE){
                        editTitle.setVisibility(View.VISIBLE);
                        imgAdd.setVisibility(View.GONE);

                        editTitleFocus = editTitle;

                        editTitleFocus.setFocusableInTouchMode(true);
                        Showlog.d("click add: " + editTitleFocus.isFocusable()+"_"+editTitle.isFocusable());
                    }else{

                    }
                }
            });
            editTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Showlog.d("focus changed: " + hasFocus);
                    if(!hasFocus){
                        hideKeyboard(v);
                        editTitle.setVisibility(View.GONE);
                        editTitleFocus = null;
                        imgAdd.setVisibility(View.VISIBLE);
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

    void handleFocus(View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(editTitleFocus!=null) {
                    Showlog.d("handle focus: " + editTitleFocus.isFocusable());
                    if(editTitleFocus.isFocusable()){
                        editTitleFocus.setFocusable(false);
                        Showlog.d("change focus to false");
                    }
                }
                return false;
            }
        });
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
