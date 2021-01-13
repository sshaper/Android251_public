package com.bignerdranch.geoquizV2

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)