package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ActionTypeSerializer : KSerializer<Action.Type> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("Type", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Action.Type) {
        encoder.encodeString(
            when(value) {
                Action.Type.Domain -> "domain"
                Action.Type.StateChange -> "state_change"
                Action.Type.Other -> "other"
            }
        )
    }

    override fun deserialize(decoder: Decoder): Action.Type {
        return when(decoder.decodeString()) {
            "domain" -> Action.Type.Domain
            "state_change" -> Action.Type.StateChange
            else -> Action.Type.Other
        }
    }
}