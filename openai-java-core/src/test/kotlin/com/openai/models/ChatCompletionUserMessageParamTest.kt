// File generated from our OpenAPI spec by Stainless.

package com.openai.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChatCompletionUserMessageParamTest {

    @Test
    fun createChatCompletionUserMessageParam() {
        val chatCompletionUserMessageParam =
            ChatCompletionUserMessageParam.builder()
                .content(ChatCompletionUserMessageParam.Content.ofString("string"))
                .role(ChatCompletionUserMessageParam.Role.USER)
                .name("string")
                .build()
        assertThat(chatCompletionUserMessageParam).isNotNull
        assertThat(chatCompletionUserMessageParam.content())
            .isEqualTo(ChatCompletionUserMessageParam.Content.ofString("string"))
        assertThat(chatCompletionUserMessageParam.role())
            .isEqualTo(ChatCompletionUserMessageParam.Role.USER)
        assertThat(chatCompletionUserMessageParam.name()).contains("string")
    }
}