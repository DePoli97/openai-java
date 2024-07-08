// File generated from our OpenAPI spec by Stainless.

package com.openai.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChatCompletionSystemMessageParamTest {

    @Test
    fun createChatCompletionSystemMessageParam() {
        val chatCompletionSystemMessageParam =
            ChatCompletionSystemMessageParam.builder()
                .content("string")
                .role(ChatCompletionSystemMessageParam.Role.SYSTEM)
                .name("string")
                .build()
        assertThat(chatCompletionSystemMessageParam).isNotNull
        assertThat(chatCompletionSystemMessageParam.content()).isEqualTo("string")
        assertThat(chatCompletionSystemMessageParam.role())
            .isEqualTo(ChatCompletionSystemMessageParam.Role.SYSTEM)
        assertThat(chatCompletionSystemMessageParam.name()).contains("string")
    }
}