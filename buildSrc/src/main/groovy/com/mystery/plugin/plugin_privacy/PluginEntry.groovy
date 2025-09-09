package com.mystery.plugin.plugin_privacy

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.mystery.plugin.plugin_privacy.transform.AnnotationParserTransform
import com.mystery.plugin.plugin_privacy.transform.PrivacyMethodReplaceTransform
import com.mystery.plugin.util.CommonUtil
import com.mystery.plugin.util.Logger
import org.gradle.api.Plugin
import org.gradle.api.Project

class PluginEntry implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println 'apply plugin privacy'

        project.extensions.create('privacy_plugin_config', PrivacySettingParams)

        def android = project.extensions.getByType(AppExtension.class)
        registerTransform(android, project)

        project.afterEvaluate {
            Logger.setIsDebug(project.privacy_plugin_config.isDebug)
            analysiUserConfig(project)
        }

    }

    static void analysiUserConfig(Project project) {
        PrivacyGlobalConfig.setDebug(project.privacy_plugin_config.isDebug)
        Logger.info("debug:: ${PrivacyGlobalConfig.isDebug}")
        PrivacyGlobalConfig.setHandleAnnotationName(CommonUtil.formatName(project.privacy_plugin_config.handle_annotation_desc))
        Logger.info("handle_annotation_desc:: ${PrivacyGlobalConfig.handleAnnotationName}")
        PrivacyGlobalConfig.setExclude(project.privacy_plugin_config.exclude)
        Logger.info("exclude:: ${PrivacyGlobalConfig.exclude}")
    }

    static void registerTransform(BaseExtension android, Project project) {
        android.registerTransform(new AnnotationParserTransform(project))

        android.registerTransform(new PrivacyMethodReplaceTransform(project))
    }
}