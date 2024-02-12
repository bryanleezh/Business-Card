package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                val systemUiController = rememberSystemUiController()
                val backgroundColor = Color(android.graphics.Color.parseColor("#d2e8d5"))

                // Set the status bar color to match the background color
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = backgroundColor,
                        darkIcons = true // Use dark icons (e.g., white icons) on the status bar
                    )
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(android.graphics.Color.parseColor("#d2e8d5")) // Set background color
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

sealed class DetailIcon {
    data class Vector(val imageVector: ImageVector): DetailIcon()
    data class Drawable(val painter: Painter) : DetailIcon()
}

@Composable
fun BusinessCard() {
    Column {
        MainDescription(
            name = "Lee Zhi Hao Bryan",
            position = "Software Engineer",
            imagePainter = painterResource(R.drawable.android_logo)
        )
        ContactInfo(
            contact = "+65 9792 7593",
            contactIcon = DetailIcon.Vector(Icons.Filled.Call),
            linkedIn = "www.linkedin.com/in/leezhihaobryan",
            linkedInIcon = DetailIcon.Drawable(painterResource(R.drawable.linkedin_logo)),
            github = "https://github.com/bryanleezh",
            githubIcon = DetailIcon.Drawable(painterResource(R.drawable.github_logo)),
            email = "leebryan307@gmail.com",
            emailIcon = DetailIcon.Vector(Icons.Filled.Email)
        )
    }
}

@Composable
fun MainDescription(
    name: String,
    position: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 180.dp)
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "main-logo",
            modifier = Modifier.size(100.dp),
        )
        Text(
            text = name,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = position,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            color = Color(android.graphics.Color.parseColor("#0b7242")),
        )

    }
}

@Composable
fun ContactInfo(
    contact: String,
    contactIcon: DetailIcon,
    linkedIn: String,
    linkedInIcon: DetailIcon,
    github: String,
    githubIcon: DetailIcon,
    email: String,
    emailIcon: DetailIcon,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Detail(
            details = contact,
            icon = contactIcon,
        )
        Detail(
            details = linkedIn,
            icon = linkedInIcon,
        )
        Detail(
            details = github,
            icon = githubIcon,
        )
        Detail(
            details = email,
            icon = emailIcon,
        )
    }
}

@Composable
fun Detail(
    details: String,
    icon: DetailIcon,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp
) {
    Row(modifier = modifier) {
        when (icon) {
            is DetailIcon.Vector -> {
                Icon(
                    imageVector = icon.imageVector,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(iconSize),
                )
            }
            is DetailIcon.Drawable -> {
                Image(
                    painter = icon.painter,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(iconSize),
                )
            }
        }
        Text(
            text = details,
            modifier = Modifier.padding(16.dp),
            color = Color(android.graphics.Color.parseColor("#0b7242")),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(android.graphics.Color.parseColor("#d2e8d5")) // Set background color
        ) {
            BusinessCard()
        }
    }
}