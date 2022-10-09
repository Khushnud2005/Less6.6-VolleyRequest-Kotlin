package uz.example.less66_volleyrequest_kotlin.network

interface VolleyHandler {
    fun onSuccess(response:String?)
    fun onError(error: String?)
}