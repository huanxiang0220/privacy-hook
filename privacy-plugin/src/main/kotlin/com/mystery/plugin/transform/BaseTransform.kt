package com.mystery.plugin.transform

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.didiglobal.booster.gradle.getAndroid
import com.didiglobal.booster.transform.AbstractKlassPool
import com.didiglobal.booster.transform.Transformer
import com.mystery.plugin.DelegateTransformInvocation
import org.gradle.api.Project

open class BaseTransform(val project: Project) : Transform() {

    internal open val transformers = listOf<Transformer>()
    internal val verifyEnabled = false
    private val android: BaseExtension = project.getAndroid()

    private lateinit var androidKlassPool: AbstractKlassPool
    val bootKlassPool: AbstractKlassPool get() = androidKlassPool

    init {
        project.afterEvaluate {
            androidKlassPool = object : AbstractKlassPool(android.bootClasspath) {}
        }
    }

    override fun getName(): String = this.javaClass.simpleName

    override fun isIncremental(): Boolean = !verifyEnabled

    override fun isCacheable(): Boolean = !verifyEnabled

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> =
        TransformManager.CONTENT_CLASS

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> =
        TransformManager.SCOPE_FULL_PROJECT

    override fun getReferencedScopes(): MutableSet<in QualifiedContent.Scope> =
        TransformManager.SCOPE_FULL_PROJECT

    override fun transform(invocation: TransformInvocation) {
        DelegateTransformInvocation(invocation,this).apply {
            if (isIncremental) {
                doIncrementalTransform()
            } else {
                doFullTransform()
            }
        }
    }

}