package com.mystery.plugin.transform

import com.didiglobal.booster.transform.Transformer
import com.mystery.plugin.asm.AnnotationParserClassTransform
import com.mystery.plugin.asm.BaseAsmTransformer
import org.gradle.api.Project

class CommonTransform(androidProject: Project) : BaseTransform(androidProject) {

    override val transformers = listOf<Transformer>(
        BaseAsmTransformer(
            listOf(
                AnnotationParserClassTransform(androidProject)
            )
        )
    )

    override fun getName(): String {
        return "CommonTransform"
    }
}