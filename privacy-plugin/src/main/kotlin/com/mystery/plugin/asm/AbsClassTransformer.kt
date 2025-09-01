package com.mystery.plugin.asm

import com.didiglobal.booster.transform.TransformContext
import com.didiglobal.booster.transform.asm.ClassTransformer
import com.didiglobal.booster.transform.asm.className
import com.mystery.plugin.extension.isRelease
import org.objectweb.asm.tree.ClassNode

open class AbsClassTransformer : ClassTransformer {

    fun onConnIntercept(context: TransformContext, klass: ClassNode): Boolean {
        if (context.isRelease())
            return true

        //过滤kotlin module-info
        if (klass.className == "module-info") {
            return true
        }
        return false
    }

}