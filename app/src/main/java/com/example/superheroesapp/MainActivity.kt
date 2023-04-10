package com.example.superheroesapp

import android.annotation.SuppressLint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.data.HeroesRepository
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.ui.theme.Shapes
import com.example.superheroesapp.ui.theme.SuperheroesAppTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HeroList(heroes = HeroesRepository.heroes)
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = { HeroTopBar() }
    ) {
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colors.background))
        {
            items(heroes) {
                HeroItem(hero = it)
            }
        }
    }
}

@Composable
fun HeroTopBar()
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground
        )
    }
}


@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
)
{
    Card(
        modifier = Modifier
            .padding(16.dp),
        elevation = 2.dp,
        shape = Shapes.large
    ){
        Row(
            Modifier
                .padding(16.dp)
                .height(72.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(3F, false),
            ){
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            }

            Image(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clip(Shapes.small)
                    .align(Alignment.CenterVertically)
                    .weight(1F, false),
                painter = painterResource(id = hero.imageRes),
                contentDescription = null,
                alignment = Alignment.CenterEnd
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    SuperheroesAppTheme {
        HeroItem(hero = HeroesRepository.heroes[3])
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModePreview()
{
    SuperheroesAppTheme (darkTheme = true){
        HeroItem(hero = HeroesRepository.heroes[3])
    }
}