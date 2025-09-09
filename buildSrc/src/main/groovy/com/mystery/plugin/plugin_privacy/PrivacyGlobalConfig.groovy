package com.mystery.plugin.plugin_privacy

import com.mystery.plugin.plugin_privacy.asmItem.MethodReplaceItem

import java.util.concurrent.LinkedBlockingQueue

class PrivacyGlobalConfig {

    public static final Queue<MethodReplaceItem> methodReplaceItemList = new LinkedBlockingQueue<>()

    public static boolean isDebug = false

    public static String handleAnnotationName

    /**
     * 用来存储替换的字节码
     */
    public static StringBuilder stringBuilder = new StringBuilder()

    /**
     * 过滤白名单
     */
    private static String[] exclude


    static boolean isDebug() {
        return isDebug
    }

    static void setDebug(boolean debug) {
        isDebug = debug
    }


    static void setHandleAnnotationName(String name) {
        handleAnnotationName = name
    }

    static String getHandleAnnotationName() {
        return handleAnnotationName
    }

    static String[] getExclude() {
        return exclude
    }

    static void setExclude(String[] excludes) {
        exclude = excludes
    }
}