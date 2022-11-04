package com.example.marvel.presentation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvel.R
import com.example.marvel.util.Constants
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
    fun MyAppNavHost(

    ) {

    }


@Destination(start = true)
@Composable
    fun CharactersScreen(navigator:DestinationsNavigator) {
    val viewModel = hiltViewModel<CharactersViewModel>()
        val state = viewModel.state
            val swipeRefreshState = rememberSwipeRefreshState(
                isRefreshing = state.isLoading
            )



            Column(
                Modifier
                    .background(Constants.getGradientVertical())
                    .fillMaxHeight()) {
                val textState = remember {
                    mutableStateOf(TextFieldValue("")) }
                Spacer(modifier = Modifier.size(10.dp))

                Image(painterResource(R.drawable.ic_marvel),
                    contentDescription = "",
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    contentScale = ContentScale.Fit,
                )

                
                Spacer(modifier = Modifier.size(18.dp))
                
                SearchView(state = textState)
                
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        viewModel.getCharacters(true)
                    },
                    Modifier.fillMaxSize()
                ) {

                    if (state.error == null) {

                        val searchedText = textState.value.text

                        Spacer(modifier = Modifier.height(5.dp))

                        LazyColumn(Modifier.padding(28.dp)) {
                            val size = state.charactersList?.size

                            if (size != null) {

                                items(size) { it ->
                                    val singleCharacter = viewModel.state.charactersList!![it]
                                    val singleCharacterName = singleCharacter.name
                                    val singleCharacterDescription = singleCharacter.description

                                    if (singleCharacterDescription.contains(searchedText, true) ||
                                        singleCharacterName.contains(searchedText, true)
                                    ) {
                                        ItemCharacter(
                                            imageUrl = singleCharacter.imageUri,
                                            characterName = singleCharacter.name,
                                            characterDescription = singleCharacter.description,
                                            navigator
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        LazyColumn(
                            Modifier
                                .background(Color.Transparent)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            item(1){
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxWidth(),
                                    text = state.error.toString(),
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }


                        }

                    }
                }


            }
        }





        @Composable
        fun SearchView(state: MutableState<TextFieldValue>) {
            TextField(
                value = state.value,
                onValueChange = { value ->
                    state.value = value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(left= 24.dp, right = 24.dp)
                    ,
                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                },
                trailingIcon = {
                    if (state.value != TextFieldValue("")) {
                        IconButton(
                            onClick = {
                                state.value =
                                    TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(28.dp), // The TextFiled has rounded corners top left and right by default
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    cursorColor = Color.White,
                    leadingIconColor = Color.White,
                    trailingIconColor = Color.White,
                    backgroundColor = colorResource(R.color.search_color),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }



