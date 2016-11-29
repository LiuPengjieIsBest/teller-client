package com.panda.teller.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.panda.teller.R;
import com.panda.teller.models.Quiz;
import com.panda.teller.utils.FileUtil;
import com.panda.teller.utils.OnItemClickListener;
import com.panda.teller.utils.StringUtil;
import com.panda.teller.views.adapters.TagFlowAdapter;
import com.panda.teller.views.widgets.FlowLayout;
import com.panda.teller.views.widgets.ImgViewLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-21.
 */

public class SubmitQuizFragment extends BaseFragment implements View.OnClickListener {

    public final int CHOOSE_PHOTO = 1;

    private View submitQuizLayout;

    /* 返回按钮 */
    private ImageView undoImg;

    /* warn框 */
    private TextView warnTxt;

    /* 两个编辑框 */
    private EditText titleEdt;
    private EditText contentEdt;

    /* 问题配图 */
    private RecyclerView quizImgListView;
    private List<Uri> quizImgs;
    private QuizImgLayoutAdapter adapter;

    /* 标签布局 */
    private FlowLayout quizTagFlow;
    private TagFlowAdapter tagFlowAdapter;

    /* 选择分区、下方提交问题按钮 */
    private Button areaBtn;
    private Button submitQuizBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        submitQuizLayout = inflater.inflate(R.layout.fragment_submit_quiz, container, false);
        quizImgs = new ArrayList<Uri>();
        initView();
        return submitQuizLayout;
    }

    public void initView() {
        undoImg = (ImageView) submitQuizLayout.findViewById(R.id.iv_fragment_submit_quiz_undo);
        warnTxt = (TextView) submitQuizLayout.findViewById(R.id.tv_fragment_submit_quiz_warn);
        titleEdt = (EditText) submitQuizLayout.findViewById(R.id.edtTxt_fragment_submit_quiz_title);
        contentEdt = (EditText) submitQuizLayout.findViewById(R.id.edtTxt_fragment_submit_quiz_content);
        /* 图片选择列表 */
        quizImgListView = (RecyclerView) submitQuizLayout.findViewById(R.id.rv_fragment_submit_quiz_img);
        quizImgListView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new QuizImgLayoutAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, Object o) {
                // TODO
            }
        });
        quizImgListView.setAdapter(adapter);
        quizImgs.add(null); /* 添加一个空的作为加号 */
        /* 问题标签布局 */
        quizTagFlow = (FlowLayout) submitQuizLayout.findViewById(R.id.flow_fragment_submit_quiz_tag);
        tagFlowAdapter = new TagFlowAdapter(getContext(), quizTagFlow);
        /* 选择分区按钮 */
        areaBtn = (Button) submitQuizLayout.findViewById(R.id.btn_fragment_submit_quiz_area);
        submitQuizBtn = (Button) submitQuizLayout.findViewById(R.id.btn_fragment_submit_quiz);
        undoImg.setOnClickListener(this);
        areaBtn.setOnClickListener(this);
        submitQuizBtn.setOnClickListener(this);
    }

    private void submit() {
        Quiz quiz = new Quiz();
        String title = titleEdt.getText().toString().trim();
        if(StringUtil.isEmpty(title)) {
            quiz.setTitle(title);
        } else {
            warnTxt.setText("请输入标题");
            return ;
        }
        String content = contentEdt.getText().toString().trim();
        if(StringUtil.isEmpty(content)) {
            quiz.setContent(content);
        } else {
            warnTxt.setText("请输入问题描述");
            return ;
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.iv_fragment_submit_quiz_undo:
            /* 隐藏当前页 */
            mActivity.hideFragment(this);
            break;
        case R.id.btn_fragment_submit_quiz_area:
            /* 选择分区按钮 */

            break;
        case R.id.btn_fragment_submit_quiz:
            /* 提交表单 */
            submit();
            break;
        default:
            break;
        }
    }

    /* 控制问题描述图片中的每一项，包括更换图片显示、添加监听 */
    class QuizImgLayoutAdapter extends RecyclerView.Adapter<QuizImgLayoutAdapter.ImgViewLayoutHolder> {

        @Override
        public ImgViewLayoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImgViewLayoutHolder holder = new ImgViewLayoutHolder(new ImgViewLayout(parent.getContext()));
            return holder;
        }

        private OnItemClickListener onItemClickListener;
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.onItemClickListener = listener;
        }

        public void addItem(int position, Uri newUri) {
            quizImgs.add(position, newUri);
            notifyItemInserted(position);
        }

        public void removeItem(int position) {
            quizImgs.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public void onBindViewHolder(ImgViewLayoutHolder holder, int position) {
            if(position == quizImgs.size() - 1) {
                /* 说明是最后一个位置 */
                holder.quizImgView.setContentRes(R.drawable.append);
                holder.quizImgView.setImgSize(50, 50);
                holder.quizImgView.setSize(300, 300);
                holder.quizImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /* 拍照或使用相册中的图片 */
                        File outputImage = FileUtil.createFile(Environment.getExternalStorageDirectory(),
                                "output_image.jpg");
                        final Uri imageUri = Uri.fromFile(outputImage);
                        Intent intent = new Intent("android.intent.action.GET_CONTENT");
                        intent.setType("image/*");
                        startActivityForResult(intent, CHOOSE_PHOTO);
                    }
                });
            } else {
                holder.quizImgView.setContentUri(quizImgs.get(position));
                holder.quizImgView.setSize(300, 300);
                holder.quizImgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /* 点击图片后放大查看 */
                        // TODO
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return quizImgs.size();
        }

        /* ViewHolder，显示照片 */
        class ImgViewLayoutHolder extends RecyclerView.ViewHolder {
            ImgViewLayout quizImgView;
            public ImgViewLayoutHolder(View v) {
                super(v);
                quizImgView = (ImgViewLayout) v;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case CHOOSE_PHOTO:
                /* 将照片Uri插入图片列表 */
                Uri uri = data.getData();
                adapter.addItem(quizImgs.size() - 1, uri);
                break;
            default:
                break;
        }
    }
}

