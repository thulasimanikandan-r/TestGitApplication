package com.manis.gitapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReposModel(
        @PrimaryKey
        var id: Long = 0,
        var name: String? = null,
        var full_name: String? = null,
        var pulls_url: String? = null
)