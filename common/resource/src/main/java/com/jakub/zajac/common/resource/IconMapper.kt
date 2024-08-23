package com.jakub.zajac.common.resource

import com.jakub.zajac.resource.R

fun getWeatherIcon(iconId: Int) : Int {
    return when(iconId) {
        1 -> R.drawable.weather_icon_1
        2 -> R.drawable.weather_icon_2
        3 -> R.drawable.weather_icon_3
        4 -> R.drawable.weather_icon_4
        5 -> R.drawable.weather_icon_5
        6 -> R.drawable.weather_icon_6
        7 -> R.drawable.weather_icon_7
        8 -> R.drawable.weather_icon_8
        11 -> R.drawable.weather_icon_11
        12 -> R.drawable.weather_icon_12
        13 -> R.drawable.weather_icon_13
        14 -> R.drawable.weather_icon_14
        15 -> R.drawable.weather_icon_15
        16 -> R.drawable.weather_icon_16
        17 -> R.drawable.weather_icon_17
        18 -> R.drawable.weather_icon_18
        19 -> R.drawable.weather_icon_19
        20 -> R.drawable.weather_icon_20
        21 -> R.drawable.weather_icon_21
        22 -> R.drawable.weather_icon_22
        23 -> R.drawable.weather_icon_23
        24 -> R.drawable.weather_icon_24
        25 -> R.drawable.weather_icon_25
        26 -> R.drawable.weather_icon_26
        29 -> R.drawable.weather_icon_29
        30 -> R.drawable.weather_icon_30
        31 -> R.drawable.weather_icon_31
        32 -> R.drawable.weather_icon_32
        33 -> R.drawable.weather_icon_33
        34 -> R.drawable.weather_icon_34
        35 -> R.drawable.weather_icon_35
        36 -> R.drawable.weather_icon_36
        37 -> R.drawable.weather_icon_37
        38 -> R.drawable.weather_icon_38
        39 -> R.drawable.weather_icon_39
        40 -> R.drawable.weather_icon_40
        41 -> R.drawable.weather_icon_41
        42 -> R.drawable.weather_icon_42
        43 -> R.drawable.weather_icon_43
        44 -> R.drawable.weather_icon_44
        else -> R.drawable.weather_icon_1
    }
}