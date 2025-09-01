package com.mystery.plugin.asm

import com.didiglobal.booster.transform.TransformContext
import com.mystery.plugin.util.LogUtils
import org.gradle.api.Project
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodInsnNode

class PrivacyMethodReplaceTransform(private var project: Project) : AbsClassTransformer() {

    private val asmItems = AnnotationParserClassTransform.asmConfigs
    private var asmItemsClassMap: HashMap<String, String> = HashMap()

    override fun onPreTransform(context: TransformContext) {
        super.onPreTransform(context)
        LogUtils.d(project, "--start-- ${System.currentTimeMillis()}")

        AnnotationParserClassTransform.asmConfigs.forEach {
            asmItemsClassMap[it.targetClass] = it.targetClass
        }
        LogUtils.d(
            project,
            "\nasmItemsMap size=${asmItemsClassMap.size}，asmItems.size=${asmItems.size}\n\n"
        )
        LogUtils.d(
            project, "asmItemsClassMap=${asmItemsClassMap} \n\n"
        )
        LogUtils.d(
            project, "asmItems=${asmItems} \n\n"
        )
    }

    override fun onPostTransform(context: TransformContext) {
        LogUtils.d(
            project, "\n --end-- ${System.currentTimeMillis()}"
        )
    }

    override fun transform(context: TransformContext, klass: ClassNode) = klass.also {
        if (onConnIntercept(context, klass)) {
            return klass
        }

        if (AnnotationParserClassTransform.asmConfigsMap.contains(klass.name)) {
            LogUtils.d(
                project,
                "\nPrivacyMethodReplaceAsmHelper modifyClass ignore,classNode.name=${klass.name}\n"
            )
            return@also
        }

        klass.methods.forEach { method ->
            method.instructions?.iterator()?.forEach { insnNode ->
                if (insnNode is MethodInsnNode) {
                    asmItems.forEach { asmItem ->
                        if (asmItem.oriDesc == insnNode.desc && asmItem.oriMethod == insnNode.name
                            && insnNode.opcode == asmItem.oriAccess &&
                            (insnNode.owner == asmItem.oriClass || asmItem.oriClass == "java/lang/Object")
                        ) {
                            LogUtils.d(
                                project,
                                "\nhook:\n" +
                                        "opcode=${insnNode.opcode},owner=${insnNode.owner},desc=${insnNode.desc},name=${klass.name}#${insnNode.name} ->\n" +
                                        "opcode=${asmItem.targetAccess},owner=${asmItem.targetClass},desc=${asmItem.targetDesc},name=${asmItem.targetMethod}\n"
                            )

                            insnNode.opcode = asmItem.targetAccess
                            insnNode.desc = asmItem.targetDesc
                            insnNode.owner = asmItem.targetClass
                            insnNode.name = asmItem.targetMethod
                        }
                    }
                }
            }
        }
    }

}