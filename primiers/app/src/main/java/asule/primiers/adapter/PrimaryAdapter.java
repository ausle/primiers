package asule.primiers.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import asule.primiers.R;

/**
 * Created by wcx on 2015/12/22.
 */
public abstract class PrimaryAdapter<T> extends RecyclerView.Adapter{

    private static final int TYPE_FOOTER = 0;
    private static final int TYPE_NORMAL = 1;
    public static final String FOOTVIEWTAG="footview";
    public List<T> mListData;

    public void setData(List<T> listData){
        this.mListData=listData;
    }
    public List<T> getData(){
        return mListData;
    }

    public Activity mActivity;
    public PrimaryAdapter(Activity activity){
        this.mActivity=activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOTER){
            View view = View.inflate(parent.getContext(), R.layout.load_more_footer, null);
            return new FootViewHolder(view);
        }
        return onCreateItem(parent,viewType);
    }

    public abstract RecyclerView.ViewHolder onCreateItem(ViewGroup parent, int viewType);
    public abstract void onBindItem(RecyclerView.ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FootViewHolder){
            holder.itemView.setTag(FOOTVIEWTAG);
            holder.itemView.setVisibility(View.GONE);
            return;
        }
        onBindItem(holder,position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return TYPE_FOOTER;
        }else{
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size()+1;
    }

    static class FootViewHolder extends RecyclerView.ViewHolder{
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
