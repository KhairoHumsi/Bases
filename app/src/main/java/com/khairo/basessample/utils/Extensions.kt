package com.khairo.basessample.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.media.RingtoneManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * these some extensions and functions for repeating using
 *
 */

fun View.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    snack(resources.getString(messageRes), length, f)
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun String.toast(context: Context?, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this, duration).apply { show() }
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}


fun log(tag: String, msg: String) {
    Log.d(tag, msg)
}

fun Context.start(to: AppCompatActivity) {
    val intent = Intent(this, to::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

fun startAffinity(context: AppCompatActivity, to: AppCompatActivity) {
    val intent = Intent(context, to::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
    context.finishAffinity()
}

fun finish(context: AppCompatActivity) {
    context.finish()
}

fun random(start: Int, end: Int): Int {
    require(start <= end) { "Illegal Argument" }
    return (start..end).random()
}

fun convertToString(bitmap: Bitmap): String {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
    val imageInByte = stream.toByteArray()
    return Base64.encodeToString(imageInByte, 0)
}

fun stringToBitMap(encodedString: String?): Bitmap? {
    return try {
        val encodeByte =
            Base64.decode(encodedString, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
    } catch (e: Exception) {
        e.message
        null
    }
}

fun getSHA512Hash(password: String): String {
    val md: MessageDigest = MessageDigest.getInstance("SHA-512")
    val messageDigest = md.digest(password.toByteArray())
    val no = BigInteger(1, messageDigest)
    var hashText: String = no.toString(16)
    while (hashText.length < 32) {
        hashText = "0$hashText"
    }

    return hashText
}

fun FragmentManager.findNavController(viewId: Int) =
    (findFragmentById(viewId) as NavHostFragment).navController

fun Fragment.findNavController(@IdRes id: Int) = activity?.let {
    return@let Navigation.findNavController(it, id)
}

fun Activity.findNavController(@IdRes id: Int) = let {
    return@let Navigation.findNavController(it, id)
}

//fun Activity.findNavController(@IdRes viewId: Int): NavController =
//    Navigation.findNavController(this, viewId)

fun Fragment.navigate(resId: Int, bundle: Bundle? = null) {
    NavHostFragment.findNavController(this).navigate(resId, bundle)
}

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.checkPermissions() {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) +
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) +
        ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) +
        ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        != PackageManager.PERMISSION_GRANTED
    ) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_SMS
            ) ||
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.RECEIVE_SMS
            )
        ) {

            AlertDialog.Builder(this)
                .setTitle("Camera, Location And SMS Permissions Needed")
                .setMessage("This app needs the Location, Camera And Sms permissions, please accept to use  functionality")
                .setPositiveButton("OK") { _, _ ->
                    //Prompt the user once explanation has been shown
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.RECEIVE_SMS
                        ), MY_PERMISSIONS_REQUEST
                    )
                }
                .create()
                .show()

        } else {

            AlertDialog.Builder(this)
                .setTitle("Camera, Location And SMS Permissions Needed")
                .setMessage("This app needs the Location, Camera And Sms permissions, please accept to use  functionality")
                .setPositiveButton("OK") { _, _ ->
                    //Prompt the user once explanation has been shown
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.RECEIVE_SMS
                        ),
                        MY_PERMISSIONS_REQUEST
                    )
                }
                .create()
                .show()
        }
    }
}

fun Context.getAddress(lat: String, lnn: String): String {
    val geocoder = Geocoder(this, Locale.getDefault())
    var ret: String

    try {
        val addresses = geocoder.getFromLocation(
            java.lang.Double.parseDouble(lat),
            java.lang.Double.parseDouble(lnn), 1
        )

        if (addresses != null) {
            val returnedAddress = addresses[0]

            val strReturnedAddress = StringBuilder()
            for (i in 0..returnedAddress.maxAddressLineIndex) {

                strReturnedAddress.append(returnedAddress.getAddressLine(i))

            }
            ret = strReturnedAddress.toString()
            if (ret.indexOf("،") != -1) {
                ret = ret.substring(0, ret.indexOf("،"))
            } else if (ret.indexOf(",") != -1) {
                ret = ret.substring(0, ret.indexOf(","))
            }
        } else {
            ret = "No Address returned!"
        }

        print("retttt= $ret")

    } catch (e: IOException) {
        println("eeeeeeeeeeee = $e")
        ret = "error!! Please reload the page!"
    }

    return ret
}

//fun AppCompatActivity.selectImage(result: (Bitmap, String) -> Unit) {
//    PickImageDialog.build(PickSetup())
//        .setOnPickResult { r ->
//            if (r.bitmap != null) {
//                result(r.bitmap, convertToString(r.bitmap))
//
//            } else {
//                App.instance.getString(R.string.action_not_supported).toast(App.instance)
//            }
//        }
//        .setOnPickCancel { toast(this, "Cancel") }
//        .show(supportFragmentManager)
//}

//fun Context.datePicker(result: (Int, Int, Int) -> Unit) {
//    val dialog = Dialog(this)
//    dialog.apply {
//        setContentView(R.layout.dialog_birth_day)
//        window!!.setBackgroundDrawableResource(android.R.color.transparent)
//
//        val today = Calendar.getInstance()
//        datePicker1.init(
//            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
//            today.get(Calendar.DAY_OF_MONTH)
//        ) { _, year, month, day ->
//            result(year, month, day)
//        }
//
//        button3.setOnClickListener {
//            dialog.cancel()
//        }
//
//        show()
//    }
//}
//
//fun Context.timePicker(result: (Int, Int, String) -> Unit) {
//    val dialog = Dialog(this)
//    dialog.apply {
//        setContentView(R.layout.dialog_time)
//        window!!.setBackgroundDrawableResource(android.R.color.transparent)
//
//        var selectedHour = 0
//        var selectedMinute = 0
//        var selectedAmPm = ""
//
//        timePicker.setOnTimeChangedListener { _, hour, minute ->
//            var pickedHour = hour
//
//            when {
//                pickedHour == 0 -> {
//                    pickedHour += 12
//                    selectedAmPm = "AM"
//                }
//
//                pickedHour == 12 -> selectedAmPm = "PM"
//
//                pickedHour > 12 -> {
//                    pickedHour -= 12
//                    selectedAmPm = "PM"
//                }
//
//                else -> selectedAmPm = "AM"
//            }
//
//            selectedHour =
//                if (pickedHour < 10) "0$pickedHour".toInt() else pickedHour.toString().toInt()
//            selectedMinute = if (minute < 10) "0$minute".toInt() else minute.toString().toInt()
//
//        }
//
//        setTime.setOnClickListener {
//            result(selectedHour, selectedMinute, selectedAmPm)
//
//            dialog.cancel()
//        }
//
//        show()
//    }
//}

fun Context.isOnline(): Boolean {
    return try {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        //should check null because in airplane mode it will be null
        netInfo != null && netInfo.isConnected
    } catch (e: NullPointerException) {
        e.printStackTrace()
        false
    }
}
//
//fun Context.registerNetworkBroadcastForNougat() {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        registerReceiver(Connection(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
//    }
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        registerReceiver(Connection(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
//    }
//}
//
//fun Context.unregisterNetworkChanges() {
//    try {
//    } catch (e: IllegalArgumentException) {
//        e.printStackTrace()
//    }
//}

fun Context.getFcm() {
//    FirebaseInstanceId.getInstance().instanceId
//        .addOnCompleteListener(OnCompleteListener { task ->
//            if (task.isSuccessful) {
//                App.instance.cacheManager.setFcmToken(task.result?.token!!)
//                log("fcmToken", App.instance.cacheManager.getFcmToken())
//                return@OnCompleteListener
//            } else {
//                "Error FirebaseInstanceId".toast(App.instance)
//            }
//        })
}

fun Context.pushNotification(title: String, desc: String) {

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "id"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Badger"
        @SuppressLint("WrongConstant") val mChannel =
            NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_MAX)
        mChannel.description = desc
        mChannel.enableLights(true)
        notificationManager.createNotificationChannel(mChannel)

    }

    showNotification(title, desc, channelId, notificationManager)
}

fun Context.showNotification(
    title: String,
    desc: String,
    channelId: String,
    notificationManager: NotificationManager
) {
//    val intent = when {
//        title.contains("has been changed") || title.contains("canceled") ->
//            Intent(this, OrdersActivity::class.java)
//
//        else ->
//            Intent(this, MainSplashActivity::class.java)
//
//    }.apply {
//        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        putExtra("isFromFcm", true)
//
//    }

//    val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
    val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val random = Random()
    val m = random.nextInt(9999 - 1000) + 1000

    val notification: Notification = NotificationCompat.Builder(this, channelId)
//        .setSmallIcon(notificationIcon)
//        .setBadgeIconType(notificationIcon)
        .setTicker(title)
        .setChannelId(channelId)
        .setAutoCancel(true)
        .setSound(alarmSound)
//        .setContentIntent(pendingIntent)
        .setContentTitle(title)
        .setNumber(1)
//        .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.logo_points))
        .setContentText(desc)
        .setStyle(NotificationCompat.BigTextStyle().bigText(desc))
        .setWhen(System.currentTimeMillis())
        .build()

    notificationManager.notify(m, notification)

}

fun String.replaceNonstandardDigits(): String {
    if (this.isEmpty()) {
        return this
    }
    val builder = StringBuilder()
    for (element in this) {
        if (element.isNonstandardDigit()) {
            val numericValue = Character.getNumericValue(element)
            if (numericValue >= 0) {
                builder.append(numericValue)
            }
        } else {
            builder.append(element)
        }
    }
    return builder.toString()
}

fun Char.isNonstandardDigit(): Boolean {
    return Character.isDigit(this) && this !in '0'..'9'
}

fun Int.setAnimation(
    viewToAnimate: View,
    lastPosition: Int,
    fromX: Float,
    toX: Float,
    fromY: Float,
    toY: Float,
    animation: Int
): Int =
    if (this > lastPosition) {
        ScaleAnimation(
            fromX, toX, fromY, toY,
            animation, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        ).run {
            duration = Random().nextInt(750).toLong()
            viewToAnimate.startAnimation(this)

        }
        this
    } else {
        this
    }

fun String.removeLastChars(chars: Int = 1): String = substring(0, length - chars)


fun DialogFragment.transparentBachGround() {
    dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}

//val notificationIcon: Int
//    get() {
////                val useWhiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
////                return if (useWhiteIcon) R.drawable.logo_points else R.drawable.logo_points
//
//        return R.drawable.logo_points
//    }


const val MY_PERMISSIONS_REQUEST = 1001