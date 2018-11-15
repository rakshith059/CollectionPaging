package quintype.com.templatecollectionwithrx.utils

interface ErrorHandler {
    fun onAPIFailure()
    fun onAPISuccess()
}