// File generated from our OpenAPI spec by Stainless.

package com.openai.services.blocking

import com.openai.core.ClientOptions
import com.openai.core.RequestOptions
import com.openai.core.http.BinaryResponseContent
import com.openai.core.http.HttpMethod
import com.openai.core.http.HttpRequest
import com.openai.core.http.HttpResponse.Handler
import com.openai.errors.OpenAIError
import com.openai.models.FileContentParams
import com.openai.models.FileCreateParams
import com.openai.models.FileDeleteParams
import com.openai.models.FileDeleted
import com.openai.models.FileListPage
import com.openai.models.FileListParams
import com.openai.models.FileObject
import com.openai.models.FileRetrieveParams
import com.openai.services.binaryHandler
import com.openai.services.errorHandler
import com.openai.services.json
import com.openai.services.jsonHandler
import com.openai.services.multipartFormData
import com.openai.services.withErrorHandler

class FileServiceImpl
constructor(
    private val clientOptions: ClientOptions,
) : FileService {

    private val errorHandler: Handler<OpenAIError> = errorHandler(clientOptions.jsonMapper)

    private val createHandler: Handler<FileObject> =
        jsonHandler<FileObject>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /**
     * Upload a file that can be used across various endpoints. Individual files can be up to 512
     * MB, and the size of all files uploaded by one organization can be up to 100 GB.
     *
     * The Assistants API supports files up to 2 million tokens and of specific file types. See the
     * [Assistants Tools guide](https://platform.openai.com/docs/assistants/tools) for details.
     *
     * The Fine-tuning API only supports `.jsonl` files. The input also has certain required formats
     * for fine-tuning [chat](https://platform.openai.com/docs/api-reference/fine-tuning/chat-input)
     * or
     * [completions](https://platform.openai.com/docs/api-reference/fine-tuning/completions-input)
     * models.
     *
     * The Batch API only supports `.jsonl` files up to 100 MB in size. The input also has a
     * specific required
     * [format](https://platform.openai.com/docs/api-reference/batch/request-input).
     *
     * Please [contact us](https://help.openai.com/) if you need to increase these storage limits.
     */
    override fun create(params: FileCreateParams, requestOptions: RequestOptions): FileObject {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .addPathSegments("files")
                .putAllQueryParams(clientOptions.queryParams)
                .putAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .putAllHeaders(params.getHeaders())
                .body(multipartFormData(clientOptions.jsonMapper, params.getBody()))
                .build()
        return clientOptions.httpClient.execute(request, requestOptions).let { response ->
            response
                .use { createHandler.handle(it) }
                .apply {
                    if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                        validate()
                    }
                }
        }
    }

    private val retrieveHandler: Handler<FileObject> =
        jsonHandler<FileObject>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /** Returns information about a specific file. */
    override fun retrieve(params: FileRetrieveParams, requestOptions: RequestOptions): FileObject {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("files", params.getPathParam(0))
                .putAllQueryParams(clientOptions.queryParams)
                .putAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .putAllHeaders(params.getHeaders())
                .build()
        return clientOptions.httpClient.execute(request, requestOptions).let { response ->
            response
                .use { retrieveHandler.handle(it) }
                .apply {
                    if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                        validate()
                    }
                }
        }
    }

    private val listHandler: Handler<FileListPage.Response> =
        jsonHandler<FileListPage.Response>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /** Returns a list of files that belong to the user's organization. */
    override fun list(params: FileListParams, requestOptions: RequestOptions): FileListPage {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("files")
                .putAllQueryParams(clientOptions.queryParams)
                .putAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .putAllHeaders(params.getHeaders())
                .build()
        return clientOptions.httpClient.execute(request, requestOptions).let { response ->
            response
                .use { listHandler.handle(it) }
                .apply {
                    if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                        validate()
                    }
                }
                .let { FileListPage.of(this, params, it) }
        }
    }

    private val deleteHandler: Handler<FileDeleted> =
        jsonHandler<FileDeleted>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

    /** Delete a file. */
    override fun delete(params: FileDeleteParams, requestOptions: RequestOptions): FileDeleted {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .addPathSegments("files", params.getPathParam(0))
                .putAllQueryParams(clientOptions.queryParams)
                .putAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .putAllHeaders(params.getHeaders())
                .apply { params.getBody().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                .build()
        return clientOptions.httpClient.execute(request, requestOptions).let { response ->
            response
                .use { deleteHandler.handle(it) }
                .apply {
                    if (requestOptions.responseValidation ?: clientOptions.responseValidation) {
                        validate()
                    }
                }
        }
    }

    private val contentHandler: Handler<BinaryResponseContent> =
        binaryHandler().withErrorHandler(errorHandler)

    /** Returns the contents of the specified file. */
    override fun content(
        params: FileContentParams,
        requestOptions: RequestOptions
    ): BinaryResponseContent {
        val request =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .addPathSegments("files", params.getPathParam(0), "content")
                .putAllQueryParams(clientOptions.queryParams)
                .putAllQueryParams(params.getQueryParams())
                .putAllHeaders(clientOptions.headers)
                .putAllHeaders(params.getHeaders())
                .build()
        return clientOptions.httpClient.execute(request, requestOptions).let { response ->
            response.let { contentHandler.handle(it) }
        }
    }
}