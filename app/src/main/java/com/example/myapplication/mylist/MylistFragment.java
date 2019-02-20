package com.example.myapplication.mylist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.myapplication.taskofllist.DetailTaskFragment;
import com.example.myapplication.util.Showlog;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MylistFragment extends Fragment implements MylistContract.View {

    public static final String KEY_BUNDLE = "KEY_BUNDLE";
    public static final String KEY_MYLIST_BUNDLE = "KEY_MYLIST_BUNDLE";
    @Inject
    RespositoryMyList respositoryMyList;

    @BindView(R.id.recyclerMyList)
    RecyclerView recyclerMyList;

    MyListAdapter mMyListAdapter;

    MylistContract.Presenter mPresenter;

    ArrayList<MyList> mMyList = new ArrayList<>();

    Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mylist,container,false);
        ButterKnife.bind(this,view);

        Showlog.d("create view");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Showlog.d("view create");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RoomComponent component = DaggerRoomComponent.builder().appModule(new AppModule(getActivity().getApplication()))
                .build();
        component.inject(this);

        init();
   //     test();

        Showlog.d("activity create");

    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        this.activity=childFragment.getActivity();
        Showlog.d("attach fm");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Showlog.d("detach fm");
    }

    @Override
    public void onStart() {
        super.onStart();
        Showlog.d("on start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Showlog.d("resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Showlog.d("pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Showlog.d("stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Showlog.d("destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Showlog.d("destroy");
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

    @Override
    public void refreshInsert(MyList myList) {
        mMyList.add(myList);
        mMyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshRemove(int position) {

    }

    @Override
    public void startDetailActivity(int position) {
        Showlog.d("start detail: " + getFragmentManager()+"_"+getActivity().getSupportFragmentManager());
        DetailTaskFragment detailTaskFragment = new DetailTaskFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_MYLIST_BUNDLE,mMyList.get(position));
        detailTaskFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        //FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameRoot,detailTaskFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}
