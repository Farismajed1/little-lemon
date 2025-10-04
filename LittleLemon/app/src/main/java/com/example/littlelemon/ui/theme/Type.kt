package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val MarkaziFont = FontFamily( Font(R.font.markazi_text_regular) )
val Karla = FontFamily( Font(R.font.karla_regular) )

// Set of Material typography styles to start with

val Typography = Typography(
    //Title
    headlineLarge = TextStyle(
        fontFamily = MarkaziFont,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp,
    ),
    //SubTitle
    headlineMedium = TextStyle(
        fontFamily = MarkaziFont,
        fontSize = 40.sp,
        color = secondaryThree
    ),
    //Section Title
    bodyLarge = TextStyle(
        fontFamily = Karla,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold
    ),
    //Card Title
    bodyMedium = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = black
    ),
    //Description
    bodySmall = TextStyle(
        fontFamily = Karla,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    )
)

