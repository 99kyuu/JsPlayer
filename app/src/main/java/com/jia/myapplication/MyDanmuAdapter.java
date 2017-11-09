package com.jia.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jia.jsplayer.danmu.DanmuAdapter;
import com.wx.goodview.GoodView;

import java.util.Random;

/**
 * Description: 弹幕适配器
 * Created by jia on 2017/9/25.
 * 人之所以能，是相信能
 */
public class MyDanmuAdapter extends DanmuAdapter<MyDanmuModel> {

    private Context context;

    private int textSize = 15;

    private float alpha = 1.0f;

    public MyDanmuAdapter(Context c) {
        super();
        context = c;
    }

    @Override
    public int[] getViewTypeArray() {
        int type[] = {0, 1, 2, 3};
        return type;
    }

    @Override
    public int getSingleLineHeight() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danmu, null);
        //指定行高
        view.measure(0, 0);

        return view.getMeasuredHeight();
    }

    @Override
    public View getView(final MyDanmuModel entry, View convertView) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_danmu, (ViewGroup) convertView, true);
            vh = new ViewHolder();
            vh.tv = convertView.findViewById(R.id.tv_danmu);
            vh.iv_danmu_img = convertView.findViewById(R.id.iv_danmu_img);
            vh.iv_danmu_good = convertView.findViewById(R.id.iv_danmu_good);
            vh.tv_good_num = convertView.findViewById(R.id.tv_good_num);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        switch (entry.getType()) {
            case 0:
                vh.iv_danmu_img.setImageResource(R.mipmap.q);

                break;
            case 1:

                vh.iv_danmu_img.setImageResource(R.mipmap.w);

                break;
            case 2:

                vh.iv_danmu_img.setImageResource(R.mipmap.e);

                break;
            case 3:

                vh.iv_danmu_img.setImageResource(R.mipmap.r);

                break;
        }

        vh.tv.setText(entry.getContent() + "");
        vh.tv.setTextSize(textSize);

        vh.tv_good_num.setText(entry.getGoodNum() + "");
        if (entry.isGood()) {
            vh.iv_danmu_good.setImageResource(R.mipmap.good_on);
        } else {
            vh.iv_danmu_good.setImageResource(R.mipmap.good_off);
        }

        vh.tv.setAlpha(alpha);
        vh.tv_good_num.setAlpha(alpha);
        vh.iv_danmu_good.setAlpha(alpha);
        vh.iv_danmu_img.setAlpha(alpha);


        final ViewHolder finalVh = vh;
        vh.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "onClick: "+entry.isGood );
                if (entry.isGood()) {
                    return;
                }
                entry.setGood(true);
                finalVh.tv_good_num.setText((entry.getGoodNum() + 1) + "");
                finalVh.tv_good_num.setTextColor(Color.RED);
                finalVh.tv.setTextColor(Color.RED);
                finalVh.iv_danmu_good.setImageResource(R.mipmap.good_on);

                GoodView goodView = new GoodView(context);
                goodView.setTextInfo("+1", Color.parseColor("#f66467"), 12);
                goodView.show(view);
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView iv_danmu_img;
        TextView tv;
        ImageView iv_danmu_good;
        TextView tv_good_num;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;

    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
