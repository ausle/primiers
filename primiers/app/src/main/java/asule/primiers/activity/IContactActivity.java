package asule.primiers.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import asule.primiers.BaseActivity;
import asule.primiers.R;
import asule.primiers.adapter.CityListAdapter;
import asule.primiers.entity.CityEntity;
import asule.primiers.entity.SortModel;
import asule.primiers.utils.PinyinComparator;
import asule.primiers.view.IContactView;

/**
 * Created by lenovo on 2016/11/9.
 */

public class IContactActivity extends BaseActivity{

    private String cityJson="";
    private List<SortModel> datas;
    private CityListAdapter cityListAdapter;
    private RecyclerView mRefreshRecyclerView;
    private EditText etCity;
    private TextView tvDialog;
    private IContactView contactView;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icontact);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mRefreshRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        etCity = (EditText) findViewById(R.id.et_city);
        tvDialog = (TextView) findViewById(R.id.dialog);
        contactView = (IContactView) findViewById(R.id.contactView);
    }

    private void initData() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRefreshRecyclerView.setLayoutManager(mLinearLayoutManager);
        cityListAdapter = new CityListAdapter(this,true);
        mRefreshRecyclerView.setAdapter(cityListAdapter);
        getNeedData();
        contactView.setTextView(tvDialog);
    }

    private void getNeedData() {
        AssetManager assets = getAssets();
        try {
            InputStream inputStream =
                    assets.open("cities.txt");
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);
            cityJson = new String(bytes, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        Gson gson=new Gson();
        CityEntity entity = gson.fromJson(cityJson, CityEntity.class);
        List<CityEntity.City> city =
                entity.getCity();
        datas = new ArrayList<>();
        for (int i = 0; i <city.size(); i++) {
            String name = city.get(i).getName();
            String pinyin = city.get(i).getPinyin();
            if (!pinyin.equals("")){
                datas.add(new SortModel(name,pinyin.substring(0,1)));
            }
        }
        Collections.sort(datas,new PinyinComparator());
        cityListAdapter.setData(datas);
        cityListAdapter.notifyDataSetChanged();
    }

    private void initListener() {
        etCity.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            @Override
            public void afterTextChanged(Editable s){
            }
        });
        contactView.setOnTouchingLetterChangedListener(new IContactView.OnTouchingLetterChangedListener(){
            @Override
            public void onTouchingLetterChanged(String s){
                // 该字母首次出现的位置
                int position = cityListAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mLinearLayoutManager.scrollToPosition(position);
                }
            }
        });
    }

    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();
        if (TextUtils.isEmpty(filterStr)){//默认显示全部
            filterDateList = datas;
        }else{
            filterDateList.clear();
            for (int i = 0; i <datas.size(); i++) {
                SortModel sortModel = datas.get(i);
                //有可能输入字母和汉字
                if (sortModel.getName().contains(filterStr)){
                    String name = sortModel.getName();
                    String sortLetters = sortModel.getSortLetters();
                    filterDateList.add(new SortModel(name,sortLetters));
                }else if (filterStr.matches("[a-z|A-Z]")){
                    if (sortModel.getSortLetters().contains(filterStr.toLowerCase())||
                            sortModel.getSortLetters().contains(filterStr.toUpperCase())){
                        String name = sortModel.getName();
                        String sortLetters = sortModel.getSortLetters();
                        filterDateList.add(new SortModel(name,sortLetters));
                    }
                }
            }
        }
        cityListAdapter.setData(filterDateList);
        cityListAdapter.notifyDataSetChanged();
    }
}
