package com.haozan.caipiao.activity;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;

/**
 * handler抽象类，防止内存泄露
 * 
 * @author peter_wang
 * @create-time 2013-11-14 上午11:08:12
 */
public abstract class WeakReferenceHandler<T>
    extends Handler {
    private WeakReference<T> mReference;

    public WeakReferenceHandler(T reference) {
        mReference = new WeakReference<T>(reference);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mReference.get() == null)
            return;
        handleMessage(mReference.get(), msg);
    }

    protected abstract void handleMessage(T reference, Message msg);
}