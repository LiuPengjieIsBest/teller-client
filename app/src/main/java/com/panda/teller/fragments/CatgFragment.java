package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.teller.R;
import com.panda.teller.models.Tag;
import com.panda.teller.utils.OnItemClickListener;
import com.panda.teller.views.widgets.CatgItemLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-8.
 */

public class CatgFragment extends BaseFragment {

    View catgLayout;

    List<Tag> tags;

    /* 点击一个分类后显示该分类的视频和问题列表 */
    SubareaFragment subareaFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        catgLayout = inflater.inflate(R.layout.fragment_catg, container, false);
        initView();
        return catgLayout;
    }

    private void initView() {
        subareaFragment = new SubareaFragment();
        /* 使用RecyclerView显示所有大区 */
        RecyclerView catgListView = (RecyclerView) catgLayout.findViewById(R.id.rv_fragment_catg_menu);
        catgListView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        CatgListAdapter adapter = new CatgListAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, Object o) {
                mActivity.showFragment(subareaFragment);
                /* 并将点击的分类所对应的Tag对象传入 */
                //subareaFragment.setTag((Tag) o);
            }
        });
        catgListView.setAdapter(adapter);

        /* 初始化分类标签 */
        tags = new ArrayList<Tag>();
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
        tags.add(new Tag());
    }

    /* 控制视频菜单中的每个单元，包括添加数据、添加监听等 */
    class CatgListAdapter extends RecyclerView.Adapter<CatgListAdapter.CatgViewHolder> {

        @Override
        public CatgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CatgViewHolder holder = new CatgViewHolder(new CatgItemLayout(parent.getContext()));
            return holder;
        }

        private OnItemClickListener onItemClickListener;
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onBindViewHolder(final CatgViewHolder holder, int position) {
            holder.cl.setCatgItem(tags.get(position));
            /* 设置监听器 */
            if(onItemClickListener != null) {
                holder.cl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /* 获取对应位置的Tag对象 */
                        Object o = tags.get(holder.getLayoutPosition());
                        /* 回调 */
                        onItemClickListener.onItemClick(v, o);
                    }
                });
            }
        }
        @Override
        public int getItemCount() {
            return tags.size();
        }

        class CatgViewHolder extends RecyclerView.ViewHolder {
            CatgItemLayout cl;
            public CatgViewHolder(View view) {
                super(view);
                cl = (CatgItemLayout) view;
            }
        }

    }



}
