package asule.primiers.holder;

import android.view.View;

public abstract class BaseHolder<T>{
	
	private View initView;
	private T mData;
	
	public BaseHolder(){
		initView = initView();
		initView.setTag(this);
	}

	public abstract View initView();
	
	public void setData(T data){
		this.mData=data;
		refreshView();
	}
	
	public T getData(){
		return mData;
	}
	
	public abstract void refreshView();
	
	public View getRootView(){
		return initView;
	}
}
