package com.mystery.privacy

import android.annotation.SuppressLint
import android.app.Application
import java.lang.reflect.InvocationTargetException

internal class AppUtils private constructor() {
    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var sApplication: Application? = null

        /**
         * Init utils.
         *
         * Init it in the class of Application.
         *
         * @param app application
         */
        fun init(app: Application?) {
            if (sApplication == null) {
                if (app == null) {
                    sApplication = applicationByReflect
                } else {
                    sApplication = app
                }
            }
        }

        val app: Application
            /**
             * Return the context of Application object.
             *
             * @return the context of Application object
             */
            get() {
                if (sApplication != null) return sApplication!!
                val app = applicationByReflect
                init(app)
                return app
            }

        private val applicationByReflect: Application
            get() {
                try {
                    @SuppressLint("PrivateApi") val activityThread =
                        Class.forName("android.app.ActivityThread")
                    val thread =
                        activityThread.getMethod("currentActivityThread").invoke(null)
                    val app = activityThread.getMethod("getApplication").invoke(thread)
                        ?: throw NullPointerException("u should init first")
                    return app as Application
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }
                throw NullPointerException("u should init first")
            }
    }

}