// File generated from our OpenAPI spec by Stainless.

package com.openai.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BatchErrorTest {

    @Test
    fun createBatchError() {
        val batchError =
            BatchError.builder().code("code").line(123L).message("message").param("param").build()
        assertThat(batchError).isNotNull
        assertThat(batchError.code()).contains("code")
        assertThat(batchError.line()).contains(123L)
        assertThat(batchError.message()).contains("message")
        assertThat(batchError.param()).contains("param")
    }
}
