// File generated from our OpenAPI spec by Stainless.

@file:Suppress("OVERLOADS_INTERFACE") // See https://youtrack.jetbrains.com/issue/KT-36102

package com.openai.services.blocking.fineTuning.jobs

import com.openai.core.RequestOptions
import com.openai.models.FineTuningJobCheckpointListPage
import com.openai.models.FineTuningJobCheckpointListParams

interface CheckpointService {

    /** List checkpoints for a fine-tuning job. */
    @JvmOverloads
    fun list(
        params: FineTuningJobCheckpointListParams,
        requestOptions: RequestOptions = RequestOptions.none()
    ): FineTuningJobCheckpointListPage
}
