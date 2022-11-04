package com.example.marvel.presentation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.marvel.R
import com.example.marvel.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailScreen(name:String?, image:String?, description:String?, navigator: DestinationsNavigator) {

    Column(
        Modifier
            .background(Constants.getGradientVertical())
            .fillMaxHeight()
            .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {

        Image(
            painterResource(R.drawable.ic_marvel),
            contentDescription = "",
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .absolutePadding(bottom = 16.dp),
            contentScale = ContentScale.Fit,

        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .fillMaxWidth(),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,

                )
            Spacer(modifier = Modifier.size(6.dp))

            TextCharacterDescription(name, description)
        }



        Spacer(modifier = Modifier.size(25.dp))
        
        Button(onClick = {navigator.popBackStack()},
        modifier = Modifier.wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.red_dark))) {
            
            Text(text = "BACK",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)
            
        }



    }

}

@Composable
fun TextCharacterDescription(name: String?,description: String?) {
    Column() {
        Text(text = name?: stringResource(R.string.non_disponibile),
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight()
            ,

            fontWeight = FontWeight.Bold,
            color = Color.White)

        Spacer(modifier = Modifier.size(4.dp))
        val textDescription = if (description?.isEmpty() == true) stringResource(R.string.non_disponibile) else description
        Text(
            text = textDescription?:stringResource(R.string.non_disponibile),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight()
                .absolutePadding(left = 16.dp, right = 16.dp)
            ,
            fontSize=18.sp,
            overflow = TextOverflow.Ellipsis,
            fontStyle = FontStyle.Italic,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )}
    }

