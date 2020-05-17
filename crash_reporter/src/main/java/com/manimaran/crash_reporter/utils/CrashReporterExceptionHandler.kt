package com.manimaran.crash_reporter.utils

class CrashReporterExceptionHandler : Thread.UncaughtExceptionHandler {
    private val exceptionHandler: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()
    override fun uncaughtException(
        thread: Thread,
        throwable: Throwable
    ) {
        CrashUtil.saveCrashReport(throwable)
        exceptionHandler?.uncaughtException(thread, throwable)
    }

}