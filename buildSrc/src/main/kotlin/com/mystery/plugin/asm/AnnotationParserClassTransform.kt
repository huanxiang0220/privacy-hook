package com.mystery.plugin.asm

import com.didiglobal.booster.transform.TransformContext
import com.mystery.plugin.util.LogUtils
import org.gradle.api.Project
import org.objectweb.asm.tree.ClassNode

class AnnotationParserClassTransform(private var project: Project) : AbsClassTransformer() {

    companion object {
        const val AsmFieldDesc = "Lcom/mystery/privacy/annotation/AsmMethodReplace;"
        var asmConfigs = mutableListOf<AsmItem>()
        var asmConfigsMap = HashMap<String, String>()
    }

    override fun onPreTransform(context: TransformContext) {
        super.onPreTransform(context)
        LogUtils.d(project, "--start-- ${System.currentTimeMillis()}")
    }

    override fun onPostTransform(context: TransformContext) {
        LogUtils.d(project, "\n--end-- ${System.currentTimeMillis()}")
    }

    override fun transform(context: TransformContext, klass: ClassNode) = klass.also {
        if (onConnIntercept(context, klass)) {
            return klass
        }

        klass.methods.forEach { method ->
            method.invisibleAnnotations?.forEach { node ->
                if (node.desc == AsmFieldDesc) {
                    val asmItem = AsmItem(klass.name, method, node)
                    if (!asmConfigs.contains(asmItem)) {
                        LogUtils.d(project, "\nadd AsmItem:$asmItem")
                        asmConfigs.add(asmItem);
                        asmConfigsMap[klass.name] = klass.name
                    }
                }
            }
        }
    }

}