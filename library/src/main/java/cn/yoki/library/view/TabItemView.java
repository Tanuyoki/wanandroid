/*
 * Copyright (c) 2019 Tanuyoki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.yoki.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import cn.yoki.library.R;


public class TabItemView extends TextView {
    private Drawable drawableLeft = null;
    private Drawable drawableTop = null;
    private Drawable drawableRight = null;
    private Drawable drawableBottom = null;
    private int drawableLeftWidth, drawableLeftHeight;
    private int drawableTopWidth, drawableTopHeight;
    private int drawableRightWidth, drawableRightHeight;
    private int drawableBottomWidth, drawableBottomHeight;

    public TabItemView(Context context) {
        this(context, null);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabItemView);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.TabItemView_drawableLeft) {
                drawableLeft = typedArray.getDrawable(attr);
            } else if (attr == R.styleable.TabItemView_drawableTop) {
                drawableTop = typedArray.getDrawable(attr);
            } else if (attr == R.styleable.TabItemView_drawableRight) {
                drawableRight = typedArray.getDrawable(attr);
            } else if (attr == R.styleable.TabItemView_drawableBottom) {
                drawableBottom = typedArray.getDrawable(attr);
            } else if (attr == R.styleable.TabItemView_drawableLeftWidth) {
                drawableLeftWidth = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.TabItemView_drawableLeftHeight) {
                drawableLeftHeight = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.TabItemView_drawableTopWidth) {
                drawableTopWidth = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.TabItemView_drawableTopHeight) {
                drawableTopHeight = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.TabItemView_drawableRightWidth) {
                drawableRightWidth = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.TabItemView_drawableRightHeight) {
                drawableRightHeight = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.TabItemView_drawableBottomWidth) {
                drawableBottomWidth = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.TabItemView_drawableBottomHeight) {
                drawableBottomHeight = typedArray.getDimensionPixelSize(attr, 0);
            }
        }

        if (null != drawableLeft) {
            drawableLeft.setBounds(0, 0, drawableLeftWidth, drawableLeftHeight);
        }
        if (null != drawableTop) {
            drawableTop.setBounds(0, 0, drawableTopWidth, drawableTopHeight);
        }
        if (null != drawableRight) {
            drawableRight.setBounds(0, 0, drawableRightWidth, drawableRightHeight);
        }
        if (null != drawableBottom) {
            drawableBottom.setBounds(0, 0, drawableBottomWidth, drawableBottomHeight);
        }
        setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

}
