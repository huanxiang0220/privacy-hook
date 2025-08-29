package com.mystery.plugin.util

import org.gradle.api.Project
import org.gradle.api.logging.Logger

object LogUtils {

    // 日志前缀，便于区分构建日志
    private const val TAG = "[BuildScript] "

    // 调试级别日志
    fun d(project: Project, message: String) {
//        log(project.logger, LogLevel.DEBUG, message)
    }

    // 信息级别日志
    fun i(project: Project, message: String) {
        log(project.logger, LogLevel.INFO, message)
    }

    // 警告级别日志
    fun w(project: Project, message: String) {
        log(project.logger, LogLevel.WARN, message)
    }

    // 错误级别日志
    fun e(project: Project, message: String) {
        log(project.logger, LogLevel.ERROR, message)
    }

    // 错误级别日志（带异常）
    fun e(project: Project, message: String, throwable: Throwable) {
        project.logger.error("$TAG$message", throwable)
    }

    // 通用日志方法
    private fun log(logger: Logger, level: LogLevel, message: String) {
        when (level) {
            LogLevel.DEBUG -> logger.debug("$TAG$message")
            LogLevel.INFO -> logger.info("$TAG$message")
            LogLevel.WARN -> logger.warn("$TAG$message")
            LogLevel.ERROR -> logger.error("$TAG$message")
        }
    }

    // 日志级别枚举
    private enum class LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

}