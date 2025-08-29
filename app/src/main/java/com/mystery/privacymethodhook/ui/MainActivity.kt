package com.mystery.privacymethodhook.ui

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
//import com.mystery.privacy.PrivacyUtil
import com.mystery.privacymethodhook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var context: Activity

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.context = this;

//        binding.run {
//            cbAgree.setOnCheckedChangeListener { compoundButton, b ->
//                PrivacyUtil.isAgreePrivacy = b
//                updateData()
//            }
//
//            cbUseCache.setOnCheckedChangeListener { compoundButton, b ->
//                PrivacyUtil.isUseCache = b
//                updateData()
//            }
//        }
//        updateData()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun updateData() {
//        binding.run {
//            val getRunningAppProcesses = getRunningAppProcesses(context)
//            btnGetRunningAppProcesses.text =
//                "getRunningAppProcesses size=${getRunningAppProcesses?.size}"
//
//            val getRecentTasks = getRecentTasks(context)
//            btnGetRecentTasks.text = ("getRecentTasks size=${getRecentTasks?.size}")
//
//            val getRunningTasks = getRunningTasks(context)
//            btnGetRunningTasks.text = ("getRunningTasks size=${getRunningTasks?.size}")
//
//            val getAllCellInfo = getAllCellInfo(context)
//            btnGetAllCellInfo.text = ("getAllCellInfo size=${getAllCellInfo?.size}")
//
//            val getDeviceId = getDeviceId(context)
//            btnGetDeviceId.text = ("getDeviceId=$getDeviceId")
//
//            getSimSerialNumber.text = ("getSimSerialNumber=${getSimSerialNumber(context)}")
//
//            val androidId =
//                Settings.System.getString(contentResolver, Settings.Secure.ANDROID_ID)
//            btnGetIdAndroid.text = ("androidId=$androidId")
//
//            getSSID.text = ("getSSID=${getSSID(context)}")
//            getBSSID.text = ("getBSSID=${getBSSID(context)}")
//            getMacAddress.text = ("getMacAddress=${getMacAddress(context)}")
//            getConfiguredNetworks.text =
//                ("getConfiguredNetworks,size=${getConfiguredNetworks(context)?.size}")
//
//            getSensorList.text = ("getSensorList size=${getSensorList(context)?.size}")
//            getImei.text = ("getImei=${getImei(context)}")
//
//            getScanResults.text = "getScanResults size=${getScanResults(context)?.size}"
//            getDhcpInfo.text = "getDhcpInfo=${getDhcpInfo(context)}"
//
//            getLastKnownLocation.text = "getLastKnownLocation=${getLastKnownLocation(context)}"
//
//            requestLocationUpdates(context)
//            requestLocationUpdates.text = "requestLocationUpdates"
//        }
    }

}