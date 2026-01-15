package com.works.project.domain.model

data class TodoData(
    val meta: Metax,
    val data: List<Daum>,
)

data class Metax(
    val status: Long,
    val message: String,
    val pagination: Pagination,
)

data class Pagination(
    val page: Long,
    val per_page: Long,
    val total_items: Long,
    val total_pages: Long,
)

data class Daum(
    val uid: Long,
    val id: Long,
    val title: String,
    val completed: Boolean,
)
