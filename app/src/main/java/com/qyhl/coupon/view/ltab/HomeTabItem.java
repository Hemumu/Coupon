package com.qyhl.coupon.view.ltab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.qyhl.coupon.R;

public class HomeTabItem extends RelativeLayout {
    private Context context;
    private ImageView imageView;
    private TextView textView;

    private Drawable checkbitmap;
    private Drawable noCheckbitmap;
    private boolean isCheck = false;
    //字体选中颜色
    private int seleColor;
    //字体未选中颜色
    private int normalColor;

    private Animatable animatable;

    public HomeTabItem(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.linearlayout_tab_item, this, true);
        imageView = (ImageView) this.findViewById(R.id.item_img);
        textView = (TextView) this.findViewById(R.id.item_text);
        animatable = new Scale2Animater();
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setCheck() {
        imageView.setImageDrawable(checkbitmap);
        animatable.onSelectChanged(imageView, true);
        textView.setTextColor(seleColor);
        isCheck = true;
    }

    public void setNoCheck() {
        imageView.setImageDrawable(noCheckbitmap);
        textView.setTextColor(normalColor);
        isCheck = false;
    }

    public void setCheckUrl(String url) {
        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                checkbitmap = resource;
                if (isCheck) {
                    imageView.setImageDrawable(checkbitmap);
                }
            }
        });
    }

    public void setNoCheckUrl(String url) {
        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                noCheckbitmap = resource;
                if (!isCheck) {
                    imageView.setImageDrawable(noCheckbitmap);
                }
            }
        });
    }

    public void setNoCheckDrawable(Drawable noCheckbitmap) {
        this.noCheckbitmap = noCheckbitmap;
    }

    public void setCheckDrawable(Drawable checkbitmap) {
        this.checkbitmap = checkbitmap;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setImgPadding(int imgPaddint) {
        textView.setPadding(0, imgPaddint, 0, 0);
    }

    public void setSeleColor(int seleColor) {
        this.seleColor = seleColor;
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
    }

    public void setTextSize(int dpsize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dpsize);
    }

    public void setImgSize(int imgWidth, int imgHeight) {
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = imgWidth;
        layoutParams.height = imgHeight;
        imageView.setLayoutParams(layoutParams);
    }
}
