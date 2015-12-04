package asule.primiers.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asule.primiers.Constants;
import asule.primiers.R;
import asule.primiers.activity.DetetailActivity;
import asule.primiers.adapter.PrimaryAdapter;
import asule.primiers.view.RefreshRecyclerView;

/**
 * Created by wcx on 2015/12/3.
 */
public class DrawerOneFragment extends BaseFragment{
    private ArrayList<String> list;
    private RefreshRecyclerView mRecyclerView;

    @Override
    protected View initView() {
        View mView = View.inflate(mActivity, R.layout.fragment_one, null);
        mRecyclerView = (RefreshRecyclerView) mView.findViewById(R.id.recyclerView);
        return mView;
    }

    @Override
    public void initData() {
        super.initData();
        String[] cheeseStrings = Constants.sCheeseStrings;
        list = new ArrayList<>();
        for (int i = 0; i <30; i++) {
            list.add(cheeseStrings[i]+"");
        }
        mRecyclerView.setAdapter(new DemoAdapter(mActivity, list));
    }

    class DemoAdapter extends PrimaryAdapter<String> {
        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> data;
        private Context mContext;
        public DemoAdapter(Context context, List<String> data) {
            super(context, data);
            this.data=data;
            this.mContext=context;
        }

        @Override
        protected RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
            mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            view.setBackgroundResource(mBackground);//手动添加波纹的效果
            return new DemoViewHolder(view);
        }

        @Override
        protected void bindHolderData(RecyclerView.ViewHolder holder, final int position) {
            if(holder instanceof DemoViewHolder){
                ((DemoViewHolder) holder).mTextView.setText(list.get(position));
                ((DemoViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, DetetailActivity.class);
                        intent.putExtra(Constants.INTENT_FLAG,data.get(position));
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public static class DemoViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTextView;
        public DemoViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
