package com.jambit.kchat.routes

import io.ktor.locations.*


@Location("/user/{id}")
data class UserRoute(val id: String)
