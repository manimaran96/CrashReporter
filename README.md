## Crash Reporter
Android Simple Crash Report/Collect Library

* Simple library to collect crashes.
* No need any third party services like firebase.

**Demo App** - [Click here](https://gitlab.com/manimaran/crashrepoter/-/jobs/556199226/artifacts/download)

**Latest version :** [![](https://jitpack.io/v/com.gitlab.manimaran/crashrepoter.svg)](https://jitpack.io/#com.gitlab.manimaran/crashrepoter)


### Screen shots

<center>
<img src="https://gitlab.com/manimaran/crashreporter/-/raw/master/files/crashreporter_1.jpg" data-canonical-src="https://gitlab.com/manimaran/crashreporter/-/raw/master/files/crashreporter_1.jpg" width="230" height="380" />
<img src="https://gitlab.com/manimaran/crashreporter/-/raw/master/files/crashreporter_2.jpg" data-canonical-src="https://gitlab.com/manimaran/crashreporter/-/raw/master/files/crashreporter_2.jpg" width="230" height="380" />

</center>


### How To Use

1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories

```xml
allprojects {
    repositories {
    	...
    	maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```xml
dependencies {
    implementation 'com.gitlab.manimaran:crashrepoter:v0.1'
}
```

3. Implementation

```kotlin
    /*Initialize Crash Reporter Configuration*/
    val config: CrashReporterConfiguration = CrashReporterConfiguration()
        
    /* Must Inisialize*/
    CrashReporter.initialize(applicationContext, config)
        .setCrashReportSendEmailIds(arrayOf("support@mail.com", "dev@mail.com"))
    
    /*Share crash information via email*/
    if(CrashUtil.isHaveCrashData)
        CrashReporter.showAlertDialogForShareCrash(this, true) // Alert dialog for confirmation of share crash logs. Here clearCrashLogsAfterShare = true/false(Default true). It will help to remove already shared crashes.
```
4. Addtional Options for crash report and configurations

```kotlin
    /*Note - Addtional Configuration options*/
    val config: CrashReporterConfiguration = CrashReporterConfiguration()
        .setExtraInformation("User ID : " + 101) // For any addtional information to be report - <NOT REQUIRED>
        .setMaxNumberOfCrashToBeReport(12) // Max number of crashes. Here, Default 5 and maximum 15 - <NOT REQUIRED>
        .setAlertDialogTheme(R.style.ThemeOverlay_AppCompat_Dialog) // Theme for alert dialog - <NOT REQUIRED>
        .setAlertDialogTitle("Do you want to share crashes?") // Title for alert dialog - <NOT REQUIRED>
        .setAlertDialogMessage("A previous crash was collected. Send the crash logs to developer to fix this issue in the future.") // Message for alert dialog - <NOT REQUIRED>
        .setAlertDialogPositiveButton("Send") // Positive button text for alert dialog - <NOT REQUIRED>
        .setAlertDialogNegativeButton("Cancel") // Negative button text for alert dialog - <NOT REQUIRED>
        .setIncludeDeviceInformation(true) // Device information include or not. Default true. - <NOT REQUIRED>
        .setCrashReportSubjectForEmail("Crash Report For " + getString(R.string.app_name) + "App") // Subject of crashes mail - <NOT REQUIRED>
        .setCrashReportSendEmailIds(arrayOf("support@mail.com", "dev@mail.com")) // Email Ids to send the crashes - <REQUIRED>
    
    /* Note - Addtional options*/
    CrashReporter.logException(e) // For collect the exceptions
    CrashUtil.isHaveCrashData // Check crash log files have or not
    CrashReporter.getAllCrashInfo() // Get all crash information then do custom action with this data. Return latest top max limit crashes data.
    CrashReporter.shareCrash(this) // Share crash messages without alert dialog.
    CrashUtil.clearAllCrashLogs() // Remove all crash log files.
    
    // Alert dilog callback
    val listener : CrashAlertClickListener = object:CrashAlertClickListener{
        override fun onOkClick() {
            TODO("Not yet implemented") // Alert positive button clicked
        }

        override fun onCancelClick() {
            TODO("Not yet implemented") // Alert negative button clicked
        }
    }
    CrashReporter.showAlertDialogForShareCrash(activity, listener, true)
    
```


### Sample Collected Information

```text
APP NAME : Crash Reporter Sample

======= DEVICE INFORMATION =======
DATE & TIME : 18-05-2020 02:10:57 am
APP.VERSION : 1
APP.VERSION_NAME : 1.0
TIMEZONE : Asia/Kolkata
PACKAGE NAME : com.manimaran.crashreporter
VERSION.RELEASE : 9
VERSION.SDK.NUMBER : 28
BRAND : xiaomi
MODEL : Redmi Note 8
MANUFACTURER : Xiaomi
DEVICE : ginkgo
BOARD : ginkgo
PRODUCT : ginkgo
ID : PKQ1.10016.001
HOST : mi-server
HARDWARE : qcom
BOOTLOADER : unknown
CPU_ABI : arm64-v8a
CPU_ABI2 : 
DISPLAY : PKQ1.190616.001
TIMESTAMP : 1584582292000

======= EXTRA INFORMATION =======
User ID : 101


======= CRASH INFORMATION =======

==================================
Crash Report : 1
==================================
Crashed Date & Time : 2020-05-18 02-10-48

java.lang.ArrayIndexOutOfBoundsException: length=2; index=10
	at com.manimaran.crashreporter.MainActivity$onCreate$2.onClick(MainActivity.kt:40)
	at android.view.View.performClick(View.java:6608)
	at android.view.View.performClickInternal(View.java:6585)
	at android.view.View.access$3100(View.java:785)
	at android.view.View$PerformClick.run(View.java:25921)
	at android.os.Handler.handleCallback(Handler.java:873)
	at android.os.Handler.dispatchMessage(Handler.java:99)
	at android.os.Looper.loop(Looper.java:201)
	at android.app.ActivityThread.main(ActivityThread.java:6864)
	at java.lang.reflect.Method.invoke(Native Method)
	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:547)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:873)


==================================
Crash Report : 2
==================================
Crashed Date & Time : 2020-05-18 02-10-47

java.lang.ArithmeticException: divide by zero
	at com.manimaran.crashreporter.MainActivity$onCreate$3.onClick(MainActivity.kt:45)
	at android.view.View.performClick(View.java:6608)
	at android.view.View.performClickInternal(View.java:6585)
	at android.view.View.access$3100(View.java:785)
	at android.view.View$PerformClick.run(View.java:25921)
	at android.os.Handler.handleCallback(Handler.java:873)
	at android.os.Handler.dispatchMessage(Handler.java:99)
	at android.os.Looper.loop(Looper.java:201)
	at android.app.ActivityThread.main(ActivityThread.java:6864)
	at java.lang.reflect.Method.invoke(Native Method)
	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:547)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:873)

=============== END ===============

```
