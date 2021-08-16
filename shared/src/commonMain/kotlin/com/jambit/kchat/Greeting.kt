package com.jambit.kchat

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}