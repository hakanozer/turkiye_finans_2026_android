package com.works.project.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
class TodoEntity {

    @PrimaryKey(autoGenerate = true)
    var tid: Long = 0


    var uid: Int = 0
    var id: Int = 0
    var title: String = ""
    var completed: Boolean = false

}