package com.mystery.plugin.transform

import com.didiglobal.booster.transform.Transformer
import com.mystery.plugin.asm.BaseAsmTransformer
import com.mystery.plugin.asm.PrivacyMethodReplaceTransform
import org.gradle.api.Project

class PrivacyMethodHookTransform(androidProject: Project) : BaseTransform(androidProject) {

    override val transformers = listOf<Transformer>(
        BaseAsmTransformer(
            listOf(
                PrivacyMethodReplaceTransform(androidProject)
            )
        )
    )

    override fun getName(): String = "PrivacyMethodHookTransform"
}