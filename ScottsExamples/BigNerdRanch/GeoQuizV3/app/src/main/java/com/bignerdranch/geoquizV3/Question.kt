package com.bignerdranch.geoquizV3

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)