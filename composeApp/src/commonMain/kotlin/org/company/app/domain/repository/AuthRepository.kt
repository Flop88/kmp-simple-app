package org.company.app.domain.repository

import org.company.app.domain.model.AppUser

interface AuthRepository {

    suspend fun doRegister(user: AppUser): Boolean

    suspend fun doLogin(email: String, password: String): Boolean

}