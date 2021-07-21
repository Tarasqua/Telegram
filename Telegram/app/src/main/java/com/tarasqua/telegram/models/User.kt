package com.tarasqua.telegram.models

data class User(
    val id: String = "",
    var userName: String = "",
    var bio: String = "",
    var fullName: String = "",
    var state: String = "",
    var photoUrl: String = "empty",
    var phone: String = ""
)