package com.manis.gitapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitModel(
        @PrimaryKey
        var id: Long = 0,
        var login: String? = null,
        var avatar_url: String? = null
)