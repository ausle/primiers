package asule.primiers.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import asule.primiers.R;
import asule.primiers.holder.BaseHolder;

public abstract class MyListViewAdapter<T> extends BaseAdapter {

	private List<T> mListData;
	private BaseHolder holder;
	private boolean isDrawer;

	public MyListViewAdapter(List<T> mListData) {
		setData(mListData);
	}

	public MyListViewAdapter(List<T> mListData,boolean isDrawer) {
		setData(mListData);
		this.isDrawer=isDrawer;
	}


	private void setData(List<T> mListData) {
		this.mListData=mListData;
	}

	public List<T> getData(){
		return mListData;
	}
	
	@Override
	public int getCount() {
		return mListData.size();
	}
	

	@Override
	public Object getItem(int position) {
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView!=null&&convertView.getTag() instanceof BaseHolder){
			holder=(BaseHolder)convertView.getTag();
		}else{
			holder=getHolder();
		}
		if (isDrawer&&position==0){
			holder.getRootView().setBackgroundResource(R.color.down_pre);
		}
		isDrawer=false;
		holder.setData(mListData.get(position));
		return holder.getRootView();
	}
	public abstract BaseHolder getHolder();
}
