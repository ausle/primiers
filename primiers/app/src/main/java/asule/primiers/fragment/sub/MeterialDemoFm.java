package asule.primiers.fragment.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import asule.primiers.adapter.PrimaryAdapter;
import asule.primiers.detail.MeterialDemoDetetail;
import asule.primiers.fragment.BaseFragment;

/**
 * Created by Administrator on 2015/12/6.
 */
public class MeterialDemoFm extends BaseFragment{

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwiperefresh;
    private View footView;
    private List<String> listAll;
    private DemoAdapter adapter;

    @Override
    protected View initView() {
        View mView = View.inflate(mActivity, R.layout.content_a_child, null);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        mSwiperefresh = (SwipeRefreshLayout) mView.findViewById(R.id.swiperefresh);
        return mView;
    }

    @Override
    public void initData() {
        super.initData();
        String[] cheeseStrings = Constants.sCheeseStrings;
        listAll = new ArrayList<>();
        for (int i = 0; i <cheeseStrings.length; i++) {
            listAll.add(cheeseStrings[i]+"");
        }
        adapter = new DemoAdapter(mActivity);
        adapter.setData(listAll);
        LinearLayoutManager manager= new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            footView.setVisibility(View.GONE);
        }
    };


    @Override
    public void initListener() {
        super.initListener();
        mSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwiperefresh.setRefreshing(false);
            }
        });
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        footView = mRecyclerView.findViewWithTag(PrimaryAdapter.FOOTVIEWTAG);
                        footView.setVisibility(View.VISIBLE);
                        handler.sendEmptyMessageDelayed(0,1000);
                    }
                }
            }
        });
    }

    public class DemoAdapter extends PrimaryAdapter<String> {
        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;

        public DemoAdapter(Activity activity) {
            super(activity);
        }

        @Override
        public RecyclerView.ViewHolder onCreateItem(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
            mActivity.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            view.setBackgroundResource(mBackground);
            return new DemoViewHolder(view);
        }

        @Override
        public void onBindItem(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof DemoViewHolder) {
                ((DemoViewHolder) holder).mTextView.setText(mListData.get(position));
                ((DemoViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, MeterialDemoDetetail.class);
                        intent.putExtra(Constants.INTENT_FLAG, mListData.get(position));
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTextView;
        public DemoViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
