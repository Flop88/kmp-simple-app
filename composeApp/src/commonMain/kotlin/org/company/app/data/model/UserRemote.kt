package org.company.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRemote(
    val uid: String,
    val email: String? = null,
    val password: String? = null,
    val registrationDate: String? = null,
)