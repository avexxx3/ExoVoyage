package com.sp4c3.exohabit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sp4c3.exohabit.ui.theme.AppTheme
import com.sp4c3.exohabit.ui.theme.montserratFamily
import com.sp4c3.exohabit.ui.theme.ralewayFamily
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        actionBar?.hide()
        setContent {
            AppTheme {
                Space()
                MainApp()
            }
        }
    }
}

@Composable
fun ExoplanetCard(planet: Exoplanet) {
    var selectedDetail: Detail? by remember { mutableStateOf(null) }
    var showDetail by remember { mutableStateOf(false) }

    Surface(color = Color.Unspecified) {
        OutlinedCard(
            border = BorderStroke(4.dp, Color.White),
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            colors = CardDefaults.outlinedCardColors()
                .copy(containerColor = Color.Black.copy(alpha = 0.75f))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    planet.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                val updateDetail: (Detail) -> Unit = { showDetail = true; selectedDetail = it }

                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                    for (i in 0..1)
                        DetailCard(planet.details[i], Modifier.weight(1f), updateDetail)
                }

                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                    for (i in 2..3)
                        DetailCard(planet.details[i], Modifier.weight(1f), updateDetail)
                }

                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                    for (i in 4..5)
                        DetailCard(planet.details[i], Modifier.weight(1f), updateDetail)
                }

                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                    for (i in 6..7)
                        DetailCard(planet.details[i], Modifier.weight(1f), updateDetail)
                }

                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                    for (i in 8..9)
                        DetailCard(planet.details[i], Modifier.weight(1f), updateDetail)
                }

                Spacer(Modifier.weight(0.5f))

                DetailCard(planet.details.last(), Modifier, updateDetail)

                LinearProgressIndicator(
                    progress = { planet.details.last().detail },
                    color = planet.color
                )

                Text(
                    "${if (!planet.habitable) "Not " else ""}Habitable",
                    fontSize = 24.sp,
                    color = if (planet.habitable) Color.Green else Color.Red,
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp)
                )

                Spacer(Modifier.weight(1f))
            }
        }

        AnimatedVisibility(showDetail, enter = fadeIn(), exit = fadeOut()) {
            InfoPopup(selectedDetail!!) { showDetail = false }
        }
    }
}

@Composable
fun DetailCard(detail: Detail, modifier: Modifier = Modifier, selectDetail: (Detail) -> Unit) {
    Box(
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clickable {
                selectDetail(detail)
            }
    ) {//.background(Color.Black.copy(alpha = 0.75f))) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                detail.type.name,
                fontSize = 24.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp),
                color = Color.White,
                textDecoration = TextDecoration.Underline
            )

            Text(
                if (detail.type.unit.isNotEmpty()) "${"%.2f".format(detail.detail)} ${detail.type.unit}" else if (detail.type == Details.Flux) "%.2f".format(
                    detail.detail
                ) else "${"%.2f".format(detail.detail * 100)}%",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun CarouselList(list: List<Exoplanet>, updatePlanet: (PlanetType) -> Unit) {
    val pagerState = rememberPagerState(list.size/2) { list.size }
    updatePlanet(list[pagerState.currentPage].planetType)

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            key = { list[it].name },
            pageSize = PageSize.Fill,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) { index ->
            ExoplanetCard(list[index])
        }
    }
}

@Composable
fun InfoPopup(detail: Detail, closePrompt: () -> Unit) {
    BackHandler {
        closePrompt()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0, 0, 0, 230))
        .clickable { closePrompt() }) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .clickable { closePrompt() }
                .padding(24.dp),
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.padding(
                        end = 8.dp
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        detail.type.name, fontSize = 24.sp,
                        fontFamily = montserratFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                Text(
                    fontFamily = montserratFamily,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(2.dp),
                    fontSize = 20.sp,
                    text = if (detail.type.unit.isNotEmpty()) "${detail.detail} ${detail.type.unit}" else if (detail.type == Details.Flux) detail.detail.toString() else "${detail.detail * 100}%"
                )

                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = detail.type.explanation,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf(Screens.Home) }

    val explorerViewModel: ExplorerViewModel = viewModel()

    var planet by remember { mutableStateOf(PlanetType.Earth) }


    BackHandler {
        currentScreen = Screens.Home
    }

    Space()

    AnimatedVisibility(
        true,
        enter = slideInVertically { -it },
        exit = slideOutVertically { -it }) {
        RotatingPlanet(planet.res)
    }

    //Home page
    AnimatedVisibility(currentScreen == Screens.Home, enter = fadeIn(), exit = fadeOut()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Logo()

            Spacer(modifier = Modifier.weight(0.5f))
            NavigationButton(
                "Explore exoplanets",
                Icons.Filled.Search
            ) {
                currentScreen = Screens.Exoplanets

                                }
//            NavigationButton(
//                "Calculate new ones",
//                Icons.Filled.Warning
//            ) { currentScreen = Screens.Creation }
            Spacer(modifier = Modifier.weight(1f))
        }
    }

    //Sliding cards
    val uiState by explorerViewModel.uiState.collectAsState()

    AnimatedVisibility(currentScreen == Screens.Exoplanets, enter = fadeIn(), exit = fadeOut()) {
        if (uiState.listOfPlanets.isNotEmpty())
            CarouselList(uiState.listOfPlanets) { planet = it }
        else
            LoadingScreen()
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "Loading...",
            color = Color.White,
            fontFamily = ralewayFamily,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun NavigationButton(nameResource: String, icon: ImageVector, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .background(Brush.linearGradient(listOf(Color.Black, Color.Black)), alpha = 0.75f),
        border = BorderStroke(2.dp, Color.White),
        shape = CutCornerShape(topStart = 0f, bottomStart = 50f, topEnd = 50f, bottomEnd = 0f),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .padding(end = 4.dp),
            tint = Color.White
        )
        Text(nameResource, color = Color.White, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun Logo() {
    Text(
        "Exo Voyage",
        textAlign = TextAlign.Center,
        color = Color.White,
        style = MaterialTheme.typography.displayLarge,
        fontSize = 72.sp,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun RotatingPlanet(painterRes: Int, saturation: Float = 1f) {
    BoxWithConstraints(contentAlignment = Alignment.Center) {
        val infiniteTransition = rememberInfiniteTransition()
        val infinitelyAnimatedAngle = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(200000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Image(
            painter = painterResource(painterRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = -maxHeight / 1.75f)
                .rotate(infinitelyAnimatedAngle.value + 90),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(saturation) })
        )
    }
}

data class Star(
    var x: Float,
    var y: Float,
    var alpha: Float,
) {
    private val initialAlpha = alpha

    fun update(value: Float) {
        val x = (value - initialAlpha).toDouble()
        val newAlpha = 0.5f + (0.5f * sin(x).toFloat())
        alpha = newAlpha
    }
}

@Composable
fun Space(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    val infinitelyAnimatedFloat = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(7500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        val width = maxWidth.value * 3
        val height = maxHeight.value * 3

        val stars = remember {
            buildList {
                repeat(1000) {
                    val x = (Math.random() * width).toFloat()
                    val y = (Math.random() * height).toFloat()
                    val alpha = (Math.random() * 2.0 * Math.PI).toFloat()
                    add(Star(x, y, alpha))
                }
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            for (star in stars) {
                star.update(infinitelyAnimatedFloat.value)
                drawCircle(
                    color = Color.White,
                    center = Offset(star.x, star.y),
                    radius = 2f,
                    alpha = star.alpha,
                )
            }
        }
    }
}

enum class Screens {
    Home,
    Exoplanets,
}