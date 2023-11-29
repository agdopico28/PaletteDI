package com.example.palettedi

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.palette.graphics.Palette


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Sitios(navHostController: NavHostController){
    Scaffold(
    ) {
        val cardDataList = getCardData()
        Box(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                content = {
                    items(cardDataList) { lazy ->
                        ItemCard(cardData = lazy, navHostController)
                    }
                })

        }
    }
}



data class CardData( //la clase principal
    var name: String,
    @DrawableRes var photo: Int
)

fun getCardData(): List<CardData> {//rellenamos los valores que despues vamos a utilizar
    return listOf(
        CardData(
            "image1",
            R.drawable.image1,
        ),
        CardData(
            "image2",
            R.drawable.image2,
        ),
        CardData(
            "image3",

            R.drawable.image3,
        ),
        CardData(
            "image4",
            R.drawable.image4,
        ),
        CardData(
            "image5",
            R.drawable.image5,
        ),

        CardData(
            "image6",
            R.drawable.image6,
        ),
        CardData(
            "image7",
            R.drawable.image7,
        ),
        CardData(
            "image8",
            R.drawable.image8,
        ),
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(cardData: CardData, navHostController: NavHostController) {


    Box(
        Modifier
            .fillMaxSize()

            .clickable { navHostController.navigate("Ampliacion/${cardData.photo}") },
        //elevation = CardDefaults.cardElevation(10.dp)
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
            ) {

                Image(
                    painter = painterResource(id = cardData.photo),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop

                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Black.copy(alpha = 0.4f)
                        ) ){
                    Text(
                        text = cardData.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        //fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                    )
                }

            }
        }


    }


}

