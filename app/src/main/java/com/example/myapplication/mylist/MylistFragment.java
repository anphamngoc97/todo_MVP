package com.example.myapplication.mylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.room.RespositoryMyList;
import com.example.myapplication.room.model.MyList;
import com.example.myapplication.roomdagger.AppModule;
import com.example.myapplication.roomdagger.DaggerRoomComponent;
import com.example.myapplication.roomdagger.RoomComponent;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MylistFragment extends Fragment implements MylistContract.View {

    @Inject
    RespositoryMyList respositoryMyList;

    @BindView(R.id.recyclerMyList)
    RecyclerView recyclerMyList;

    MyListAdapter mMyListAdapter;

    MylistContract.Presenter mPresenter;

    ArrayList<MyList> mMyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mylist,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RoomComponent component = DaggerRoomComponent.builder().appModule(new AppModule(getActivity().getApplication()))
                .build();
        component.inject(this);

        init();
   //     test();
    }
    private void init(){
        mPresenter = new MylistPresenterImpl(this,new MylistIteratorImpl(respositoryMyList));
        mMyListAdapter = new MyListAdapter(mMyList,mPresenter,recyclerMyList);
        recyclerMyList.setAdapter(mMyListAdapter);

        mPresenter.getData();
    }

    @Override
    public void refreshList(ArrayList<MyList> myList) {
        mMyList.clear();
        mMyList.addAll(myList);
        mMyListAdapter.notifyDataSetChanged();
        Log.d("AAA","size refresh: " + myList.size());
    }


}
