package com.example.android.timepickerdialogpreferencepractice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class Calendar extends DialogPreference {
    private int DEFAULT_VALUE = 0;
    private int mTime = DEFAULT_VALUE;
    //private int mDialogLayoutResId = R.layout.timepicker_dialog;
    private NumberPicker mNumberPicker;

    public Calendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.timepicker_dialog);
        //indicate to the super Preference class that you persist the preference value on your own
        //setPersistent(false);
        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
        setDialogIcon(null);
    }

    public Calendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, android.R.attr.preferenceStyle);
    }

    @Override
    protected void onBindDialogView(View view) {
        mNumberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(10);
        mNumberPicker.setValue(mTime);
        super.onBindDialogView(view);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        //default value from attribute. fallback value is set to 0;
        //The method arguments provide everything you need: the array of
        // attributes and the index position of the android:defaultValue,
        // which you must retrieve.
        return a.getInt(index,DEFAULT_VALUE);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue){
            mTime = getPersistedInt(DEFAULT_VALUE);
        }else{
            mTime = (int) defaultValue;
            persistInt(mTime);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult){
            mTime = mNumberPicker.getValue();
            if (callChangeListener(mTime)){
                persistInt(mTime);
            }
        }
    }

}
