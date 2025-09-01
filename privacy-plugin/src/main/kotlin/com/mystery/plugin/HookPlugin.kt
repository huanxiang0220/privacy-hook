package com.mystery.plugin

import com.android.build.gradle.AppExtension
import com.didiglobal.booster.gradle.getAndroid
import com.mystery.plugin.transform.CommonTransform
import com.mystery.plugin.transform.PrivacyMethodHookTransform
import com.mystery.plugin.util.LogUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class HookPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        //不带表达式（当作 if-else 链使用）：
        when {
            project.plugins.hasPlugin("com.android.application") || project.plugins.hasPlugin("com.android.dynamic-feature") -> {
                LogUtils.d(project, "com.android.application || com.android.dynamic-feature.")

                project.getAndroid<AppExtension>().let {
                    it.registerTransform(CommonTransform(project))
                    it.registerTransform(PrivacyMethodHookTransform(project))
                }
            }

            project.hasProperty("com.android.library") -> {
                LogUtils.d(project, "com.android.library.")
            }

            else -> {
                LogUtils.d(project, "else -> HookPlugin only works for Android projects.")
            }
        }
    }

}