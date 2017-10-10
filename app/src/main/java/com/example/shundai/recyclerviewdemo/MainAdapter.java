package com.example.shundai.recyclerviewdemo;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by win7 on 2017/10/9.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_TYPE_PROGRESS = 1;
    private static final int ITEM_TYPE_IMAGE = 2;
    private static final int ITEM_TYPE_STRING = 3;


    private Context mContext;
    private List<News> mDatas;
    private OnItemClickListener onItemClickListener;
    private final LayoutInflater inflater;
    private OnItemLongClickListener onItemLongClickListener;

    public MainAdapter(Context context, List<News> datas) {
        mContext = context;
        mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<News> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<News> mDatas) {
        this.mDatas = mDatas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (ITEM_TYPE_PROGRESS == viewType) {
            view = inflater.inflate(R.layout.item_image, null);
            return new MyProgressViewholder(view);
        } else if (ITEM_TYPE_STRING == viewType) {
            view = inflater.inflate(R.layout.item_main, null);
            return new MyViewHolder(view);
        }
        if (ITEM_TYPE_IMAGE == viewType) {
            view = inflater.inflate(R.layout.item_image2, null);
            return new MyImageViewHolder(view);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(adapterPosition, view, holder);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(adapterPosition, view, holder);
                }
                return true;
            }
        });

        if (holder instanceof MyProgressViewholder) {
            ((MyProgressViewholder) holder).tv1.setText("正在加载中...");
        } else if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).tv.setText(mDatas.get(position).getContent());
        } else if (holder instanceof MyImageViewHolder) {
            ((MyImageViewHolder) holder).iv.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.tongliya));
        }

    }


    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view, RecyclerView.ViewHolder vh);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position, View view, RecyclerView.ViewHolder vh);

    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mDatas.size() ) {
            return ITEM_TYPE_PROGRESS;
        } else if (mDatas.get(position).getType()==1) {
            return ITEM_TYPE_IMAGE;
        } else if (mDatas.get(position).getType()==0){
            return ITEM_TYPE_STRING;
        }else {
            return super.getItemViewType(position);
        }

    }

    static class MyProgressViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.pb)
        ProgressBar pb;
        @BindView(R.id.tv1)
        TextView tv1;

        public MyProgressViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;


        public MyImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
