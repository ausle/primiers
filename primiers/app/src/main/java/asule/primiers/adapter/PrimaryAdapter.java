package asule.primiers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import asule.primiers.R;

/**
 * Created by wcx on 2015/12/3.
 */
public abstract class PrimaryAdapter<T> extends RecyclerView.Adapter {

    private Context mContext;
    private List<T> mData;
    private final int FOOT_POSITION=1;

    public PrimaryAdapter(Context context,List<T> data){
        this.mContext=context;
        this.mData=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==FOOT_POSITION){
            View footView = LayoutInflater.from(mContext).inflate(R.layout.load_more_footer, null);
            return new FootViewHolder(footView);
        }
        return getViewHolder(parent,viewType);
    }

    protected abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType);
    protected abstract void bindHolderData(RecyclerView.ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FootViewHolder){
            holder.itemView.setTag("foot");
            return;
        }
        bindHolderData(holder,position);
    }

    @Override
    public int getItemViewType(int position) {
        if(position+1>=getItemCount()){
            return FOOT_POSITION;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

   public static class FootViewHolder extends RecyclerView.ViewHolder{
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
