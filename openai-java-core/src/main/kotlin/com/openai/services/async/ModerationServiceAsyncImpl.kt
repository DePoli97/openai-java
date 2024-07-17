// File generated from our OpenAPI spec by Stainless.

package com.openai.services.async

import com.openai.core.ClientOptions
import com.openai.core.RequestOptions
import com.openai.core.http.HttpMethod
import com.openai.core.http.HttpRequest
import com.openai.core.http.HttpResponse.Handler
import com.openai.errors.OpenAIError
import com.openai.models.ModerationCreateParams
import com.openai.models.ModerationCreateResponse
import com.openai.services.errorHandler
import com.openai.services.json
import com.openai.services.jsonHandler
import com.openai.services.withErrorHandler
import java.util.concurrent.CompletableFuture

class ModerationServiceAsyncImpl
constructor(
    private val clientOptions: ClientOptions,
) : ModerationServiceAsync {

    private val errorHandler: Handler<OpenAIError> = errorHandler(clientOptions.jsonMapper)

    private val createHandler: Handler<ModerationCreateResponse> =
        jsonHandler<ModerationCreateResponse>(clientOptions.jsonMapper)
            .withErrorHandler(errorHandler)

    /** Classifies if text is potentially harmful. */
    override fun create(
        params: ModerationCreateParams,
        requestOptions: RequestOptions
    ): CompletableFuture<ModerationCreateResponse> {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("moderations")
                .putAllQueryParams(clientOptions.queryParams)
                .putAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .putAllHeaders(params.getHeaders())
                .body(json(clientOptions.jsonMapper, params.getBody()))
                .build()
        return clientOptions.httpClient.executeAsync(request, requestOptions).thenApply { response
            ->
            response
                .use { createHandler.handle(it) }
                .apply {
                    if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                        validate()
                    }
                }
        }
    }
}
