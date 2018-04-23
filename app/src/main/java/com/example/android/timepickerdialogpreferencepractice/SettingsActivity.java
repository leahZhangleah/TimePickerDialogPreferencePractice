package com.example.android.timepickerdialogpreferencepractice;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);
            Preference numberPicker = findPreference("fromTime");
            bindPreferenceSummaryToValue(numberPicker);
        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
           if (preference instanceof Calendar){
               Calendar calendar = (Calendar) preference;
               calendar.setSummary(stringValue);
           }
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference){
            //init Preference summary
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            int preferenceInt = preferences.getInt(preference.getKey(),0);
            onPreferenceChange(preference,preferenceInt);

            preference.setOnPreferenceChangeListener(this);
        }
    }

}















