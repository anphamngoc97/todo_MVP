package com.example.myapplication.taskofllist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.mylist.MylistFragment;
import com.example.myapplication.room.model.MyList;
import com.example.myapplication.util.Showlog;
import com.example.myapplication.util.TypeEventbus.RefreshMylist;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTaskFragment extends Fragment {

    @BindView(R.id.btnDetail)
    Button btnDetail;
    @BindView(R.id.btnEvent)
    Button btnEvent;

    MyList myList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_task,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getData();
        init();

    }
    private void getData(){
        Bundle bundle = getArguments();
        if(bundle!=null){
            myList = (MyList) bundle.getSerializable(MylistFragment.KEY_MYLIST_BUNDLE);
            if(myList!=null){
                Showlog.d("get Detail: " + myList.name);
            }
        }
    }

    private void init(){
        btnDetail.setOnClickListener(v->{
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameRoot,new MylistFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
        btnEvent.setOnClickListener(v->{
            EventBus.getDefault().postSticky(new RefreshMylist());
        });
    }
}
