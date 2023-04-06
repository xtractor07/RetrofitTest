package com.example.retrofittest.model

data class InitUploadResponse(
    val data: Data,
    val status: String
)

data class Data(
    val alwaysCompressed: Boolean,
    val async: Any,
    val bucketType: String,
    val compressionEnabled: Boolean,
    val contentLength: Any,
    val contentType: String,
    val createdByUserId: Any,
    val createdTime: Int,
    val dealerId: Any,
    val deleted: Boolean,
    val encodingFormat: Any,
    val expectedCompressedSizeInBytes: Int,
    val fileName: String,
    val fragment: Any,
    val generateThumbnail: Any,
    val globalMedia: Boolean,
    val indexInLibrary: Any,
    val internal: Boolean,
    val mediaCategory: String,
    val mediaId: String,
    val mimeType: String,
    val modifiedTime: Int,
    val objectURL: String,
    val publicEligible: Boolean,
    val quarantineEligible: Boolean,
    val stripMetadata: Boolean,
    val tags: Any,
    val tenantId: Any,
    val thumbnailTimeStamp: Any,
    val validForSeconds: Int
)