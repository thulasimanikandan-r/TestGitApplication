package com.manis.gitapp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun <T> navigateToActivity(context: Context, className: Class<T>, bundle: Bundle?) {
    val intent = Intent(context, className)
    if (bundle != null) intent.putExtras(bundle)
    context.startActivity(intent)
}