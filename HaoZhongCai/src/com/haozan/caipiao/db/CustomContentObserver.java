/*******************************************************************************
 * Copyright 2013 Zhang Zhuo(william@TinyGameX.com). Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *******************************************************************************/
package com.haozan.caipiao.db;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

/**
 * 数据库观察者
 * 
 * @author peter_wang
 * @create-time 2013-10-24 上午11:03:05
 */
public class CustomContentObserver
    extends ContentObserver {
    public static final int UPDATE_CONTENT_VIEW = 1000;

    private Context context;
    private Handler handler;

    public CustomContentObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        handler.sendEmptyMessage(UPDATE_CONTENT_VIEW);
    }
}
