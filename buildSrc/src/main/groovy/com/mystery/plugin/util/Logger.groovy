package com.mystery.plugin.util

class Logger {

    private static boolean debug = false

    static void setIsDebug(boolean isDebug) {
        debug = isDebug
    }

    /**
     * 打印日志
     */
    def static info(Object msg) {
        if (!debug) {
            return
        }
        try {
            println "${msg}"
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

}