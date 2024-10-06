package com.sp4c3.exohabit

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class ExplorerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExplorerUiState())
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()

    var error: Boolean = false
    var listOfPlanets: MutableList<Exoplanet> = mutableListOf()

    init {
        listOfPlanets.add(
            Exoplanet(
                name = "EPIC JABBAR AZEEM",
                details = listOf(
                    Detail(
                        type = Details.Distance, detail = 1342f
                    ),
                    Detail(
                        type = Details.Density, detail = 2.12f
                    ),
                    Detail(
                        type = Details.Temp, detail = 802f
                    ),
                    Detail(
                        type = Details.Mass, detail = 1234f
                    ),
                    Detail(
                        type = Details.Radius, detail = 190f
                    ),
                    Detail(
                        type = Details.Flux, detail =70f
                    ),
                    Detail(
                        type = Details.Period, detail = 13.34f
                    ),
                    Detail(
                        type = Details.ECC, detail =0.5f
                    ),
                    Detail(
                        type = Details.Gravity, detail =0.7f
                    ),
                    Detail(
                        type = Details.ESI, detail =0.7f
                    ),
                    Detail(
                        type = Details.Habitability, detail = 0.9f
                    ),
                ),
                color = Color(1f * (0.5f), 1f * 0.5f, 0f),
                habitable = true,
                planetType = PlanetType.Unknown
            ),
        )

        listOfPlanets.add(Exoplanet(
            name = "EPIC JABBAR aAZEEM",
            details = listOf(
                Detail(
                    type = Details.Distance, detail = 1342f
                ),
                Detail(
                    type = Details.Density, detail = 2.12f
                ),
                Detail(
                    type = Details.Temp, detail = 802f
                ),
                Detail(
                    type = Details.Mass, detail = 1234f
                ),
                Detail(
                    type = Details.Radius, detail = 190f
                ),
                Detail(
                    type = Details.Flux, detail =70f
                ),
                Detail(
                    type = Details.Period, detail = 13.34f
                ),
                Detail(
                    type = Details.ECC, detail =0.5f
                ),
                Detail(
                    type = Details.Gravity, detail =0.7f
                ),
                Detail(
                    type = Details.ESI, detail =0.7f
                ),
                Detail(
                    type = Details.Habitability, detail = 0.9f
                ),
            ),
            color = Color(100.8f* 0.7f, 0.78f, 0.6f),
            habitable = true,
            planetType = PlanetType.Rocky
        ))

        listOfPlanets.add(Exoplanet(
            name = "EPIC JABBAR AZE",
            details = listOf(
                Detail(
                    type = Details.Distance, detail = 1342f
                ),
                Detail(
                    type = Details.Density, detail = 2.12f
                ),
                Detail(
                    type = Details.Temp, detail = 802f
                ),
                Detail(
                    type = Details.Mass, detail = 1234f
                ),
                Detail(
                    type = Details.Radius, detail = 190f
                ),
                Detail(
                    type = Details.Flux, detail =70f
                ),
                Detail(
                    type = Details.Period, detail = 13.34f
                ),
                Detail(
                    type = Details.ECC, detail =0.5f
                ),
                Detail(
                    type = Details.Gravity, detail =0.7f
                ),
                Detail(
                    type = Details.ESI, detail =0.7f
                ),
                Detail(
                    type = Details.Habitability, detail = 0.9f
                ),
            ),
            color = Color(100.8f* 0.7f, 0.78f, 0.6f),
            habitable = true,
            planetType = PlanetType.IceGiant
        ))

        listOfPlanets.add(Exoplanet(
            name = "EPIC JABBAR AZEE",
            details = listOf(
                Detail(
                    type = Details.Distance, detail = 1342f
                ),
                Detail(
                    type = Details.Density, detail = 2.12f
                ),
                Detail(
                    type = Details.Temp, detail = 802f
                ),
                Detail(
                    type = Details.Mass, detail = 1234f
                ),
                Detail(
                    type = Details.Radius, detail = 190f
                ),
                Detail(
                    type = Details.Flux, detail =70f
                ),
                Detail(
                    type = Details.Period, detail = 13.34f
                ),
                Detail(
                    type = Details.ECC, detail =0.5f
                ),
                Detail(
                    type = Details.Gravity, detail =0.7f
                ),
                Detail(
                    type = Details.ESI, detail =0.7f
                ),
                Detail(
                    type = Details.Habitability, detail = 0.9f
                ),
            ),
            color = Color(100.8f* 0.7f, 0.78f, 0.6f),
            habitable = true,
            planetType = PlanetType.GasGiant
        ))


        //falseData()
    }

    private fun updateUI() {
        _uiState.update {
            it.copy(
                error = error,
                listOfPlanets = listOfPlanets
            )
        }
    }

    private fun falseData() {
        val json = "[{"
                "name: " +
                "\"EPIC 220674823 c\", " +
                "distance:" +
                "244.59, " +
                "density: "  +
                "2.12, " +
                "temp:" +
                "805," +
                "mass:" +
                "8.9," +
                "radius:" +
                "2.836," +
                "flux:" +
                "70," +
                "period:" +
                "13.3397," +
                "eccentricity:" +
                "0.13," +
                "gravity:" +
                "10.84435258," +
                "esi:" +
                "0.23523564," +
                "habitability_score:" +
                "21.761782," +
                "habitable:" +
                "false," +
                "surface_type:" +
                "\"Ice Giant\"}]"

        val gson = Gson()
        val listType = object : TypeToken<List<jsonResponse?>?>() {}.type
        val listOfResponse:List<jsonResponse> = gson.fromJson(json, listType)



        for(response in listOfResponse)             {
            listOfPlanets.add(
                Exoplanet(
                    name = response.name,
                    details = listOf(
                        Detail(
                            type = Details.Distance, detail = response.distance
                        ),
                        Detail(
                            type = Details.Density, detail = response.density
                        ),
                        Detail(
                            type = Details.Temp, detail =response.temp
                        ),
                        Detail(
                            type = Details.Mass, detail =response.mass
                        ),
                        Detail(
                            type = Details.Radius, detail =response.radius
                        ),
                        Detail(
                            type = Details.Flux, detail =response.flux
                        ),
                        Detail(
                            type = Details.Period, detail =response.period
                        ),
                        Detail(
                            type = Details.ECC, detail =response.eccentricity
                        ),
                        Detail(
                            type = Details.Gravity, detail =response.gravity
                        ),
                        Detail(
                            type = Details.ESI, detail =response.esi
                        ),
                        Detail(
                            type = Details.Habitability, detail = response.habitability/100f
                        ),
                    ),

                    color = Color(1f * (1 - response.habitability), 1f * response.habitability, 0f),
                    habitable = response.habitable,
                    planetType = when(response.planetType) {
                        "Ice Giant" -> PlanetType.IceGiant
                        "Rocky" -> PlanetType.Rocky
                        "Gas Giant" -> PlanetType.GasGiant
                        else -> PlanetType.Unknown
                    }
                )
            )
        }

        updateUI()
    }

    private fun fetchData() {
        val url = ""
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                error = true
                updateUI()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.body == null) {
                    error = true
                    updateUI()
                }

                val gson = Gson()
                val listType = object : TypeToken<List<Exoplanet?>?>() {}.type
                listOfPlanets = gson.fromJson(response.body!!.string(), listType)
                updateUI()
            }
        })
    }
}

data class Detail(
    val type: Details,
    val detail: Float
)

enum class Details(val unit: String, val explanation: String) {
    Distance("AU", "One AU is the average distance between Earth and the Sun."),
    Density("g/cm^3", "One g/cm^3 corresponds to one thousandth of a kilogram per cubic meter and expresses the mass of the body relative to its volume."),
    Temp("K", "A temperature in Centigrade can be expressed in Kelvin by adding 273 to its value."),
    Mass("M\uD83D\uDF28", "One M\uD83D\uDF28 is a unit of mass equal to the mass of the planet Earth."),
    Radius("R\uD83D\uDF28", "R\uD83D\uDF28 is a unit of distance equal to the radius of the planet Earth."),
    Flux("", "The flux of the star"),
    Period("days", "The orbital period in Earth days"),
    ECC("", "A measure of how similar the orbit is to a perfect sphere, with 1 being a perfect sphere"),
    Gravity("g\uD83D\uDF28/n", "g\uD83D\uDF28 is referred to as the acceleration of gravity. Its value is 9.8 m/s^2 on Earth"),
    ESI("", "A measure of how similar the planet's characteristics are to that of Earth, with 1 being a perfect copy of Earth"),
    Habitability("", "A measure of how adequate the planetary environment is for human living")
}

enum class PlanetType(val res:Int) {
    IceGiant(R.drawable.icy),
    Unknown(R.drawable.unknown),
    Rocky(R.drawable.rocky),
    GasGiant(R.drawable.gassy),
    Earth(R.drawable.earth)
}

data class Exoplanet(
    val name: String,
    val details: List<Detail>,
    val habitable: Boolean,
    val planetType: PlanetType,
    var color: Color
)

data class jsonResponse(
    val name: String,
    val distance: Float,
    val density: Float,
    val temp: Float,
    val mass: Float,
    val radius: Float,
    val flux: Float,
    val period: Float,
    val eccentricity: Float,
    val gravity: Float,
    val esi: Float,
    val habitability: Float,
    val habitable: Boolean,
    val planetType: String
)

data class ExplorerUiState(
    var error: Boolean = false,
    var listOfPlanets: List<Exoplanet> = listOf()
)