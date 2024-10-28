package org.lhq.param

enum class AccountSortBy(private val value: String) : SortBy {
    CREATED_AT_ASC("created_at.asc"),
    CREATED_AT_DESC("created_at.desc");




    override fun getValue() = value

}