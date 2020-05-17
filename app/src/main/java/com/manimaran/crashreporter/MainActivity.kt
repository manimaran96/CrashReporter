package com.manimaran.crashreporter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.manimaran.crash_reporter.CrashReporter
import com.manimaran.crash_reporter.CrashReporterConfiguration
import com.manimaran.crash_reporter.utils.CrashUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Initialize Crash Reporter Configuration*/
        val config: CrashReporterConfiguration = CrashReporterConfiguration()
            .setExtraInformation("User ID : " + 101)
            .setMaxNumberOfCrashToBeReport(12)
            .setAlertDialogPositiveButton("Send")
            .setCrashReportSubjectForEmail("Crash Report For " + getString(R.string.app_name))
            .setCrashReportSendEmailIds(arrayOf("support@mail.com", "dev@mail.com"))

        CrashReporter.initialize(applicationContext, config)

        btnNullPointer.setOnClickListener {
            val view: View? = null
            view!!.id
        }

        btnArrayIndexOutOfFound.setOnClickListener {
            val a = arrayOf(1, 2)
            val c = a[10]
        }

        btnArithmetic.setOnClickListener {
            try {
                val a = 3 / 0
            } catch (e: Exception) {
                CrashReporter.logException(e)
                loadCount()
            }
        }

        loadCount()

        btnSendCrash.setOnClickListener {
            if (CrashUtil.isHaveCrashData)
                CrashReporter.showAlertDialogForShareCrash(this, true)
            else
                Toast.makeText(applicationContext, "Crash Data Not found", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    override fun onResume() {
        super.onResume()
        loadCount()
    }

    private fun loadCount() {
        txtCrashDetails.text = ("Collected Crashes : ${CrashUtil.crashLogsCount}")
    }
}


