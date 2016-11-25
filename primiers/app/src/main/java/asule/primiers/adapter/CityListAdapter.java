package asule.primiers.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import asule.primiers.R;
import asule.primiers.entity.SortModel;

/**
 * Created by lenovo on 2016/11/9.
 */

public class CityListAdapter extends DemoAdapter<SortModel>{

    public CityListAdapter(Activity activity) {
        super(activity);
    }

    public CityListAdapter(Activity activity, boolean isWithOutFoot) {
        super(activity, isWithOutFoot);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItem(ViewGroup parent, int viewType) {
        View view =View.inflate(mActivity, R.layout.item_city_selecter,null);
        return new CityListViewHolder(view);
    }

    @Override
    public void onBindItem(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CityListViewHolder){
            SortModel sortModel = mListData.get(position);
            //根据position获取分类的首字母的Char ascii值
            int section = getSectionForPosition(position);
            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if(position == getPositionForSection(section)){
                ((CityListViewHolder) holder).tv1.setVisibility(View.VISIBLE);
                ((CityListViewHolder) holder).tv1.setText(sortModel.getSortLetters());
            }else{
                ((CityListViewHolder) holder).tv1.setVisibility(View.GONE);
            }
            ((CityListViewHolder) holder).tv2.setText(sortModel.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity,mListData.get(position).getName(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return mListData.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < mListData.size(); i++) {
            String sortStr = mListData.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }



    public class CityListViewHolder extends RecyclerView.ViewHolder{

        public TextView tv1;
        public TextView tv2;

        public CityListViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.catalog);
            tv2 = (TextView) itemView.findViewById(R.id.title);
        }
    }
}




