package com.mystery.privacymethodhook.ui;

import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getAllCellInfo;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getBSSID;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getConfiguredNetworks;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getDeviceId;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getDhcpInfo;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getImei;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getLastKnownLocation;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getMacAddress;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getRecentTasks;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getRunningAppProcesses;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getRunningTasks;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getSSID;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getScanResults;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getSensorList;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.getSimSerialNumber;
import static com.mystery.privacymethodhook.ui.TopLevelUtilKt.requestLocationUpdates;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mystery.privacy.PrivacyConfig;
import com.mystery.privacymethodhook.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.context = this;

        binding.cbAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            PrivacyConfig.setAgreePrivacyDialog(isChecked);
            updateData();
        });
        binding.cbUseCache.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateData();
        });
        updateData();
    }

    private void updateData() {
        TextView btnGetRunningAppProcesses = binding.btnGetRunningAppProcesses;
        TextView btnGetRecentTasks = binding.btnGetRecentTasks;
        TextView btnGetRunningTasks = binding.btnGetRunningTasks;
        TextView btnGetAllCellInfo = binding.btnGetAllCellInfo;
        TextView btnGetDeviceId = binding.btnGetDeviceId;
        TextView getSimSerialNumber = binding.getSimSerialNumber;
        TextView btnGetIdAndroid = binding.btnGetIdAndroid;
        TextView getSSID = binding.getSSID;
        TextView getBSSID = binding.getBSSID;
        TextView getMacAddress = binding.getMacAddress;
        TextView getSensorList = binding.getSensorList;
        TextView getImei = binding.getImei;
        TextView getScanResults = binding.getScanResults;
        TextView getConfiguredNetworks = binding.getConfiguredNetworks;
        TextView getDhcpInfo = binding.getDhcpInfo;
        TextView getLastKnownLocation = binding.getLastKnownLocation;
        TextView requestLocationUpdates = binding.requestLocationUpdates;

        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = getRunningAppProcesses(context);
        btnGetRunningAppProcesses.setText(String.format("getRunningAppProcesses size=%s", runningAppProcesses.size()));

        btnGetRecentTasks.setText(String.format("getRecentTasks size=%s", getRecentTasks(context).size()));

        btnGetRunningTasks.setText(String.format("getRunningTasks size=%s", getRunningTasks(context).size()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btnGetAllCellInfo.setText(String.format("getAllCellInfo size=%s", getAllCellInfo(context).size()));
            btnGetDeviceId.setText(String.format("getDeviceId=%s", getDeviceId(context)));
            getSimSerialNumber.setText(String.format("getSimSerialNumber=%s", getSimSerialNumber(context)));

            String androidId =
                    Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            btnGetIdAndroid.setText(String.format("androidId=%s", androidId));

            getSSID.setText(String.format("getSSID=%s", getSSID(context)));
            getBSSID.setText(String.format("getBSSID=%s", getBSSID(context)));
            getMacAddress.setText(String.format("getMacAddress=%s", getMacAddress(context)));
//            getConfiguredNetworks.setText(String.format("getConfiguredNetworks size=%s", getConfiguredNetworks(context).size()));

//            getSensorList.setText(String.format("getSensorList size=%s", getSensorList(context).size()));
            getImei.setText(String.format("getImei=%s", getImei(context)));

//            getScanResults.setText(String.format("getScanResults size=%s", getScanResults(context).size()));
            getDhcpInfo.setText(String.format("getDhcpInfo=%s", getDhcpInfo(context)));

            getLastKnownLocation.setText(String.format("getLastKnownLocation=%s", getLastKnownLocation(context)));

            requestLocationUpdates(context);
            requestLocationUpdates.setText(String.format("requestLocationUpdates=%s", requestLocationUpdates));
        }
    }

}
