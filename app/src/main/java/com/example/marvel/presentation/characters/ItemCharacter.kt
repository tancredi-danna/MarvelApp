package com.example.marvel.presentation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.marvel.R
import com.example.marvel.presentation.characters.destinations.CharactersScreenDestination
import com.example.marvel.presentation.characters.destinations.DetailScreenDestination
import com.example.marvel.ui.screen.Screen
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable

fun ItemCharacter(
    imageUrl: String,
    characterName: String,
    characterDescription: String,
    navigator: DestinationsNavigator
){



        Card(modifier =
        Modifier
            .fillMaxWidth()
            .absolutePadding(left = 12.dp, top = 12.dp)
            .clickable {
                       navigator.navigate(DetailScreenDestination(name = characterName,image= imageUrl, description = characterDescription))
            }
            ,
            backgroundColor = colorResource(id = R.color.color_card),
            shape = RoundedCornerShape(12.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(160.dp)
                        .absolutePadding(top = 6.dp)
                        ,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit,

                )


                Spacer(modifier = Modifier.height(5.dp))



                Text(
                    text = characterName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                    ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(5.dp))

                val textDescription =
                    if (characterDescription.isEmpty()) "Al momento la descrizione non è disponibile" else characterDescription


                Text(
                    text = textDescription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .absolutePadding(left = 16.dp, right = 16.dp)
                    ,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(15.dp))



            }
        }


}
@Composable
fun ImageLoader(url: String) {

    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = "car image",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(75.dp)
            .fillMaxWidth()
            .height(100.dp)
    )
}