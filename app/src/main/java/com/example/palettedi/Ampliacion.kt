package com.example.palettedi

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.palette.graphics.Palette

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ammpliacion(
    navControllerImagen: Int,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val textContacto = navBackStackEntry?.arguments?.getString("textoContacto") ?: ""


    val context = LocalContext.current

    /* Convert our Image Resource into a Bitmap */
    val bitmap = remember {
        BitmapFactory.decodeResource(context.resources, navControllerImagen)
    }

    /* Create the Palette, pass the bitmap to it */
    val palette = remember {
        Palette.from(bitmap).generate()
    }

    /* Get the dark vibrant swatch */
    val darkVibrantSwatch = palette.darkVibrantSwatch
    val lightVibrantSwatch = palette.lightVibrantSwatch
    val vibrantSwatch = palette.vibrantSwatch
    val lightMutedSwatch = palette.lightMutedSwatch
    val mutedSwatch = palette.mutedSwatch
    val darkMutedSwatch = palette.darkMutedSwatch

    //Cambia el color de la barra de arriba del todo
    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor =
        (darkVibrantSwatch?.let { Color(it.rgb).toArgb() } ?: Color.Red.toArgb())

    var isMenuVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(//barra de menu parte superior de la pantalla
                title = {
                    Text(
                        text = "Palette",
                        color = vibrantSwatch?.let { Color(it.titleTextColor) } ?: Color.Red,
                        fontSize = 20.sp) //nombre que aparece en la barra
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(//Icono de las tres barras horizontales
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Row() {
                        IconButton( //Icono de los tres puntos que cuando clicas aparece el dropdownMenu
                            onClick = {
                                isMenuVisible = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    vibrantSwatch?.let { Color(it.rgb) } ?: Color.Transparent
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
//                .padding(top = it.calculateTopPadding()),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = navControllerImagen),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
            )


            // Mostrar filas con colores
            ColorRow("Dark Vibrant", darkVibrantSwatch)
            ColorRow("Light Vibrant", lightVibrantSwatch)
            ColorRow("Vibrant", vibrantSwatch)
            ColorRow("Light Muted", lightMutedSwatch)
            ColorRow("Muted", mutedSwatch)
            ColorRow("Dark Muted", darkMutedSwatch)
        }
    }
}


@Composable
fun ColorRow(name: String, color: Palette.Swatch?) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(1f)
                .background(color?.rgb?.let { Color(it) } ?: Color.Transparent)
        )

    }
}