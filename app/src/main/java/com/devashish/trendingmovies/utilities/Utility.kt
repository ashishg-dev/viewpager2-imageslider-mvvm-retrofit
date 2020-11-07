package com.devashish.trendingmovies.utilities

import java.text.SimpleDateFormat
import java.util.*

class Utility {

    companion object {

        fun getFormattedDate(data: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val sourceDate = sdf.parse(data)
            val targetSDF = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
            return targetSDF.format(sourceDate!!)
        }

    }

}