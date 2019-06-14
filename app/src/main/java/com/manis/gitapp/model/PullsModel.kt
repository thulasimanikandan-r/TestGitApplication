package com.manis.gitapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PullsModel(
        @PrimaryKey
        var id: Long = 0,
        var state: String? = null,
        var url: String? = null,
        var title: String? = null,
        var number: Int? = 0,
        var open_issues_count: Int? = 0)