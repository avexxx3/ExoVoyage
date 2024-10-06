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
import java.io.InputStream


class ExplorerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExplorerUiState())
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()

    var error: Boolean = false
    var listOfPlanets: MutableList<Exoplanet> = mutableListOf()

    init {
        fetchData()
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
        listOfPlanets.add(
            Exoplanet(
                name = "EPIC 220674823 c",
                details = listOf(
                    Detail(type = Details.Distance, detail = 244.59F),
                    Detail(type = Details.Density, detail = 2.12F),
                    Detail(type = Details.Temp, detail = 805f),
                    Detail(type = Details.Mass, detail = 8.9f),
                    Detail(type = Details.Radius, detail = 2.836f),
                    Detail(type = Details.Flux, detail = 70f),
                    Detail(type = Details.Period, detail = 13.3397f),
                    Detail(type = Details.ECC, detail = 0.13f),
                    Detail(type = Details.Gravity, detail = 10.84435258f),
                    Detail(type = Details.ESI, detail = 0.23523564f),
                    Detail(type = Details.Habitability, detail = 21.761782f / 100f)
                ),
                color = Color(1f * (1 - 21.761782f / 100f), 1f * (21.761782f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "EPIC 229004835 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 121.971f),
                    Detail(type = Details.Density, detail = 4.49f),
                    Detail(type = Details.Temp, detail = 804f),
                    Detail(type = Details.Mass, detail = 10.4f),
                    Detail(type = Details.Radius, detail = 2.332f),
                    Detail(type = Details.Flux, detail = 69.7f),
                    Detail(type = Details.Period, detail = 16.141132f),
                    Detail(type = Details.ECC, detail = 0.23f),
                    Detail(type = Details.Gravity, detail = 18.74141264f),
                    Detail(type = Details.ESI, detail = 0.25645062f),
                    Detail(type = Details.Habitability, detail = 12.822531000000001f / 100f)
                ),
                color = Color(1f * (1 - 12.822531000000001f / 100f), 1f * (12.822531000000001f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Unknown
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "EPIC 249893012 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 321.296f),
                    Detail(type = Details.Density, detail = 6.39f),
                    Detail(type = Details.Temp, detail = 1616f),
                    Detail(type = Details.Mass, detail = 8.75f),
                    Detail(type = Details.Radius, detail = 1.95f),
                    Detail(type = Details.Flux, detail = 1037f),
                    Detail(type = Details.Period, detail = 3.5951f),
                    Detail(type = Details.ECC, detail = 0.06f),
                    Detail(type = Details.Gravity, detail = 22.55095332f),
                    Detail(type = Details.ESI, detail = 0.25843827f),
                    Detail(type = Details.Habitability, detail = 32.9219135f / 100f)
                ),
                color = Color(1f * (1 - 32.9219135f / 100f), 1f * (32.9219135f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Rocky
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "EPIC 249893012 c",
                details = listOf(
                    Detail(type = Details.Distance, detail = 321.296f),
                    Detail(type = Details.Density, detail = 1.62f),
                    Detail(type = Details.Temp, detail = 990f),
                    Detail(type = Details.Mass, detail = 14.67f),
                    Detail(type = Details.Radius, detail = 3.67f),
                    Detail(type = Details.Flux, detail = 160f),
                    Detail(type = Details.Period, detail = 15.624f),
                    Detail(type = Details.ECC, detail = 0.07f),
                    Detail(type = Details.Gravity, detail = 10.67392289f),
                    Detail(type = Details.ESI, detail = 0.19451622f),
                    Detail(type = Details.Habitability, detail = 19.725811f / 100f)
                ),
                color = Color(1f * (1 - 19.725811f / 100f), 1f * (19.725811f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "EPIC 249893012 d",
                details = listOf(
                    Detail(type = Details.Distance, detail = 321.296f),
                    Detail(type = Details.Density, detail = 0.91f),
                    Detail(type = Details.Temp, detail = 752f),
                    Detail(type = Details.Mass, detail = 10.18f),
                    Detail(type = Details.Radius, detail = 3.94f),
                    Detail(type = Details.Flux, detail = 53f),
                    Detail(type = Details.Period, detail = 35.747f),
                    Detail(type = Details.ECC, detail = 0.15f),
                    Detail(type = Details.Gravity, detail = 6.426602077f),
                    Detail(type = Details.ESI, detail = 0.20209233f),
                    Detail(type = Details.Habitability, detail = 10.104616499999999f / 100f)
                ),
                color = Color(1f * (1 - 10.104616499999999f / 100f), 1f * (10.104616499999999f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 1132 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 12.613f),
                    Detail(type = Details.Density, detail = 5.97f),
                    Detail(type = Details.Temp, detail = 584f),
                    Detail(type = Details.Mass, detail = 1.83705f),
                    Detail(type = Details.Radius, detail = 1.192f),
                    Detail(type = Details.Flux, detail = 19.3f),
                    Detail(type = Details.Period, detail = 1.62892911f),
                    Detail(type = Details.ECC, detail = 0.0118f),
                    Detail(type = Details.Gravity, detail = 12.67052301f),
                    Detail(type = Details.ESI, detail = 0.36158321f),
                    Detail(type = Details.Habitability, detail = 48.0791605f / 100f)
                ),
                color = Color(1f * (1 - 48.0791605f / 100f), 1f * (48.0791605f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Rocky
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 1214 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 14.6427f),
                    Detail(type = Details.Density, detail = 2.2f),
                    Detail(type = Details.Temp, detail = 596f),
                    Detail(type = Details.Mass, detail = 8.17f),
                    Detail(type = Details.Radius, detail = 2.742f),
                    Detail(type = Details.Flux, detail = 21f),
                    Detail(type = Details.Period, detail = 1.58040433f),
                    Detail(type = Details.ECC, detail = 0.063f),
                    Detail(type = Details.Gravity, detail = 10.64910799f),
                    Detail(type = Details.ESI, detail = 0.27839216f),
                    Detail(type = Details.Habitability, detail = 23.919608f / 100f)
                ),
                color = Color(1f * (1 - 23.919608f / 100f), 1f * (23.919608f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 3473 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 27.3644f),
                    Detail(type = Details.Density, detail = 5.03f),
                    Detail(type = Details.Temp, detail = 773f),
                    Detail(type = Details.Mass, detail = 1.86f),
                    Detail(type = Details.Radius, detail = 1.264f),
                    Detail(type = Details.Flux, detail = 59.4f),
                    Detail(type = Details.Period, detail = 1.1980035f),
                    Detail(type = Details.ECC, detail = 0f),
                    Detail(type = Details.Gravity, detail = 11.40892886f),
                    Detail(type = Details.ESI, detail = 0.31135508f),
                    Detail(type = Details.Habitability, detail = 45.567754f / 100f)
                ),
                color = Color(1f * (1 - 45.567754f / 100f), 1f * (45.567754f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Rocky
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 367 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 9.41263f),
                    Detail(type = Details.Density, detail = 10.2f),
                    Detail(type = Details.Temp, detail = 1365f),
                    Detail(type = Details.Mass, detail = 0.633f),
                    Detail(type = Details.Radius, detail = 0.699f),
                    Detail(type = Details.Flux, detail = 579f),
                    Detail(type = Details.Period, detail = 0.3219225f),
                    Detail(type = Details.ECC, detail = 0.06f),
                    Detail(type = Details.Gravity, detail = 12.69624909f),
                    Detail(type = Details.ESI, detail = 0.28429496f),
                    Detail(type = Details.Habitability, detail = 24.214748f / 100f)
                ),
                color = Color(1f * (1 - 24.214748f / 100f), 1f * (24.214748f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Unknown
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 3929 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 15.8095f),
                    Detail(type = Details.Density, detail = 7.3f),
                    Detail(type = Details.Temp, detail = 568f),
                    Detail(type = Details.Mass, detail = 1.75f),
                    Detail(type = Details.Radius, detail = 1.09f),
                    Detail(type = Details.Flux, detail = 17.3f),
                    Detail(type = Details.Period, detail = 2.616235f),
                    Detail(type = Details.ECC, detail = 0f),
                    Detail(type = Details.Gravity, detail = 14.43481188f),
                    Detail(type = Details.ESI, detail = 0.370086f),
                    Detail(type = Details.Habitability, detail = 28.5043f / 100f)
                ),
                color = Color(1f * (1 - 28.5043f / 100f), 1f * (28.5043f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Unknown
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-58 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 515.59f),
                    Detail(type = Details.Density, detail = 0.194f),
                    Detail(type = Details.Temp, detail = 1622f),
                    Detail(type = Details.Mass, detail = 118.23217f),
                    Detail(type = Details.Radius, detail = 14.93f),
                    Detail(type = Details.Flux, detail = 1146f),
                    Detail(type = Details.Period, detail = 4.0138379f),
                    Detail(type = Details.ECC, detail = 0.073f),
                    Detail(type = Details.Gravity, detail = 5.198069966f),
                    Detail(type = Details.ESI, detail = 0.061600215f),
                    Detail(type = Details.Habitability, detail = 0.0308001075F)
                ),
                color = Color(1f * (1f - 0.0308001075f), 1f * 0.0308001075f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-59 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 642.884f),
                    Detail(type = Details.Density, detail = 0.7f),
                    Detail(type = Details.Temp, detail = 1128f),
                    Detail(type = Details.Mass, detail = 256.17098f),
                    Detail(type = Details.Radius, detail = 12.621f),
                    Detail(type = Details.Flux, detail = 269f),
                    Detail(type = Details.Period, detail = 5.416081f),
                    Detail(type = Details.ECC, detail = 0.129f),
                    Detail(type = Details.Gravity, detail = 15.76044486f),
                    Detail(type = Details.ESI, detail = 0.074513172f),
                    Detail(type = Details.Habitability, detail = 3.7256586f / 100f)
                ),
                color = Color(1f * (1 - 3.7256586f / 100f), 1f * (3.7256586f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-60 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 486.474f),
                    Detail(type = Details.Density, detail = 0.537f),
                    Detail(type = Details.Temp, detail = 1528f),
                    Detail(type = Details.Mass, detail = 210.40346f),
                    Detail(type = Details.Radius, detail = 12.924f),
                    Detail(type = Details.Flux, detail = 903.9f),
                    Detail(type = Details.Period, detail = 3.560829f),
                    Detail(type = Details.ECC, detail = 0.191f),
                    Detail(type = Details.Gravity, detail = 12.34482831f),
                    Detail(type = Details.ESI, detail = 0.070213614f),
                    Detail(type = Details.Habitability, detail = 13.5106807f / 100f)
                ),
                color = Color(1f * (1 - 13.5106807f / 100f), 1f * (13.5106807f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-61 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 679.761f),
                    Detail(type = Details.Density, detail = 2.47f),
                    Detail(type = Details.Temp, detail = 1226f),
                    Detail(type = Details.Mass, detail = 1080.622f),
                    Detail(type = Details.Radius, detail = 13.395f),
                    Detail(type = Details.Flux, detail = 374.2f),
                    Detail(type = Details.Period, detail = 7.817953f),
                    Detail(type = Details.ECC, detail = 0.092f),
                    Detail(type = Details.Gravity, detail = 59.02207107f),
                    Detail(type = Details.ESI, detail = 0.069721617f),
                    Detail(type = Details.Habitability, detail = 3.48608085f / 100f)
                ),
                color = Color(1f * (1 - 3.48608085f / 100f), 1f * (3.48608085f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-62 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 523.067f),
                    Detail(type = Details.Density, detail = 0.076f),
                    Detail(type = Details.Temp, detail = 1237f),
                    Detail(type = Details.Mass, detail = 56.89157f),
                    Detail(type = Details.Radius, detail = 11.825f),
                    Detail(type = Details.Flux, detail = 387.4f),
                    Detail(type = Details.Period, detail = 3.2768837f),
                    Detail(type = Details.ECC, detail = 0.298f),
                    Detail(type = Details.Gravity, detail = 3.987233741f),
                    Detail(type = Details.ESI, detail = 0.07733512f),
                    Detail(type = Details.Habitability, detail = 3.8667559999999996f / 100f)
                ),
                color = Color(1f * (1 - 3.8667559999999996f / 100f), 1f * (3.8667559999999996f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-63 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 623.232f),
                    Detail(type = Details.Density, detail = 0.67f),
                    Detail(type = Details.Temp, detail = 1398f),
                    Detail(type = Details.Mass, detail = 305.1168f),
                    Detail(type = Details.Radius, detail = 13.529f),
                    Detail(type = Details.Flux, detail = 634.1f),
                    Detail(type = Details.Period, detail = 3.0566527f),
                    Detail(type = Details.ECC, detail = 0.136f),
                    Detail(type = Details.Gravity, detail = 16.33656718f),
                    Detail(type = Details.ESI, detail = 0.067956845f),
                    Detail(type = Details.Habitability, detail = 3.39784225f / 100f)
                ),
                color = Color(1f * (1 - 3.39784225f / 100f), 1f * (3.39784225f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-64 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 1080.15f),
                    Detail(type = Details.Density, detail = 0.245f),
                    Detail(type = Details.Temp, detail = 1793f),
                    Detail(type = Details.Mass, detail = 305.1168f),
                    Detail(type = Details.Radius, detail = 18.82f),
                    Detail(type = Details.Flux, detail = 1711f),
                    Detail(type = Details.Period, detail = 4.908897f),
                    Detail(type = Details.ECC, detail = 0.151f),
                    Detail(type = Details.Gravity, detail = 8.44214794f),
                    Detail(type = Details.ESI, detail = 0.049722146f),
                    Detail(type = Details.Habitability, detail = 12.4861073f / 100f)
                ),
                color = Color(1f * (1 - 12.4861073f / 100f), 1f * (12.4861073f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-65 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 493.32f),
                    Detail(type = Details.Density, detail = 0.3f),
                    Detail(type = Details.Temp, detail = 1634f),
                    Detail(type = Details.Mass, detail = 260.93843f),
                    Detail(type = Details.Radius, detail = 16.825f),
                    Detail(type = Details.Flux, detail = 1178f),
                    Detail(type = Details.Period, detail = 3.105161f),
                    Detail(type = Details.ECC, detail = 0.062f),
                    Detail(type = Details.Gravity, detail = 9.033456861f),
                    Detail(type = Details.ESI, detail = 0.055330205f),
                    Detail(type = Details.Habitability, detail = 12.76651025f / 100f)
                ),
                color = Color(1f * (1 - 12.76651025f / 100f), 1f * (12.76651025f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-66 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 1477.98f),
                    Detail(type = Details.Density, detail = 2.34f),
                    Detail(type = Details.Temp, detail = 1998f),
                    Detail(type = Details.Mass, detail = 1694.0339f),
                    Detail(type = Details.Radius, detail = 15.816f),
                    Detail(type = Details.Flux, detail = 2637f),
                    Detail(type = Details.Period, detail = 3.1414391f),
                    Detail(type = Details.ECC, detail = 0.064f),
                    Detail(type = Details.Gravity, detail = 66.36740854f),
                    Detail(type = Details.ESI, detail = 0.057991268f),
                    Detail(type = Details.Habitability, detail = 2.8995634f / 100f)
                ),
                color = Color(1f * (1 - 2.8995634f / 100f), 1f * (2.8995634f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-67 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 960.109f),
                    Detail(type = Details.Density, detail = 0.374f),
                    Detail(type = Details.Temp, detail = 2193f),
                    Detail(type = Details.Mass, detail = 460.8535f),
                    Detail(type = Details.Radius, detail = 18.887f),
                    Detail(type = Details.Flux, detail = 3821f),
                    Detail(type = Details.Period, detail = 1.6091788f),
                    Detail(type = Details.ECC, detail = 0.057f),
                    Detail(type = Details.Gravity, detail = 12.66085413f),
                    Detail(type = Details.ESI, detail = 0.049227641f),
                    Detail(type = Details.Habitability, detail = 12.461382050000001f / 100f)
                ),
                color = Color(1f * (1 - 12.461382050000001f / 100f), 1f * (12.461382050000001f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-68 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 603.955f),
                    Detail(type = Details.Density, detail = 0.856f),
                    Detail(type = Details.Temp, detail = 1741f),
                    Detail(type = Details.Mass, detail = 410.0007f),
                    Detail(type = Details.Radius, detail = 13.809f),
                    Detail(type = Details.Flux, detail = 1521f),
                    Detail(type = Details.Period, detail = 3.5862202f),
                    Detail(type = Details.ECC, detail = 0.036f),
                    Detail(type = Details.Gravity, detail = 21.0710518f),
                    Detail(type = Details.ESI, detail = 0.065787328f),
                    Detail(type = Details.Habitability, detail = 3.2893664000000005f / 100f)
                ),
                color = Color(1f * (1 - 3.2893664000000005f / 100f), 1f * (3.2893664000000005f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-69 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 414.537f),
                    Detail(type = Details.Density, detail = 0.46f),
                    Detail(type = Details.Temp, detail = 1296f),
                    Detail(type = Details.Mass, detail = 183.38791f),
                    Detail(type = Details.Radius, detail = 10.593f),
                    Detail(type = Details.Flux, detail = 466.6f),
                    Detail(type = Details.Period, detail = 2.2252577f),
                    Detail(type = Details.ECC, detail = 0.519f),
                    Detail(type = Details.Gravity, detail = 16.01617599f),
                    Detail(type = Details.ESI, detail = 0.084188369f),
                    Detail(type = Details.Habitability, detail = 4.20941845f / 100f)
                ),
                color = Color(1f * (1 - 4.20941845f / 100f), 1f * (4.20941845f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-70 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 1458.83f),
                    Detail(type = Details.Density, detail = 6.64f),
                    Detail(type = Details.Temp, detail = 2730f),
                    Detail(type = Details.Mass, detail = 4100.007f),
                    Detail(type = Details.Radius, detail = 15.513f),
                    Detail(type = Details.Flux, detail = 9110f),
                    Detail(type = Details.Period, detail = 1.8882378f),
                    Detail(type = Details.ECC, detail = 0.18f),
                    Detail(type = Details.Gravity, detail = 166.9625576f),
                    Detail(type = Details.ESI, detail = 0.058723425f),
                    Detail(type = Details.Habitability, detail = 22.93617125f / 100f)
                ),
                color = Color(1f * (1 - 22.93617125f / 100f), 1f * (22.93617125f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Rocky
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-72 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 127.589f),
                    Detail(type = Details.Density, detail = 0.411f),
                    Detail(type = Details.Temp, detail = 739f),
                    Detail(type = Details.Mass, detail = 39.85588f),
                    Detail(type = Details.Radius, detail = 8.097f),
                    Detail(type = Details.Flux, detail = 49.58f),
                    Detail(type = Details.Period, detail = 7.3279474f),
                    Detail(type = Details.ECC, detail = 0.013f),
                    Detail(type = Details.Gravity, detail = 5.957584347f),
                    Detail(type = Details.ESI, detail = 0.12504644f),
                    Detail(type = Details.Habitability, detail = 6.252322f / 100f)
                ),
                color = Color(1f * (1 - 6.252322f / 100f), 1f * (6.252322f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-74 A b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 299.389f),
                    Detail(type = Details.Density, detail = 1.64f),
                    Detail(type = Details.Temp, detail = 895f),
                    Detail(type = Details.Mass, detail = 464.02947f),
                    Detail(type = Details.Radius, detail = 11.568f),
                    Detail(type = Details.Flux, detail = 106.9f),
                    Detail(type = Details.Period, detail = 1.73185606f),
                    Detail(type = Details.ECC, detail = 0.044f),
                    Detail(type = Details.Gravity, detail = 33.98248069f),
                    Detail(type = Details.ESI, detail = 0.086108284f),
                    Detail(type = Details.Habitability, detail = 4.3054141999999995f / 100f)
                ),
                color = Color(1f * (1 - 4.3054141999999995f / 100f), 1f * (4.3054141999999995f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-75 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 194.964f),
                    Detail(type = Details.Density, detail = 0.878f),
                    Detail(type = Details.Temp, detail = 1234f),
                    Detail(type = Details.Mass, detail = 119.97744f),
                    Detail(type = Details.Radius, detail = 10.557f),
                    Detail(type = Details.Flux, detail = 250.1f),
                    Detail(type = Details.Period, detail = 3.01832073f),
                    Detail(type = Details.ECC, detail = 0.025f),
                    Detail(type = Details.Gravity, detail = 12.55486033f),
                    Detail(type = Details.ESI, detail = 0.061458166f),
                    Detail(type = Details.Habitability, detail = 5.2782043999999995f / 100f)
                ),
                color = Color(1f * (1 - 5.2782043999999995f / 100f), 1f * (5.2782043999999995f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-76 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 150.703f),
                    Detail(type = Details.Density, detail = 0.75f),
                    Detail(type = Details.Temp, detail = 1130f),
                    Detail(type = Details.Mass, detail = 99.71529f),
                    Detail(type = Details.Radius, detail = 9.433f),
                    Detail(type = Details.Flux, detail = 291.1f),
                    Detail(type = Details.Period, detail = 1.81816073f),
                    Detail(type = Details.ECC, detail = 0.06f),
                    Detail(type = Details.Gravity, detail = 8.60398864f),
                    Detail(type = Details.ESI, detail = 0.09077956f),
                    Detail(type = Details.Habitability, detail = 3.8949794999999997f / 100f)
                ),
                color = Color(1f * (1 - 3.8949794999999997f / 100f), 1f * (3.8949794999999997f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )


        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-59 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 265.471f),
                    Detail(type = Details.Density, detail = 1.347f),
                    Detail(type = Details.Temp, detail = 1278f),
                    Detail(type = Details.Mass, detail = 489.45575f),
                    Detail(type = Details.Radius, detail = 12.588f),
                    Detail(type = Details.Flux, detail = 441.4f),
                    Detail(type = Details.Period, detail = 4.1419771f),
                    Detail(type = Details.ECC, detail = 0.03f),
                    Detail(type = Details.Gravity, detail = 30.2709494f),
                    Detail(type = Details.ESI, detail = 0.073103653f),
                    Detail(type = Details.Habitability, detail = 0.0365518265F)
                ),
                color = Color(1f * (1 - 0.0365518265f), 1f * 0.0365518265f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-60 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 233.22f),
                    Detail(type = Details.Density, detail = 0.164f),
                    Detail(type = Details.Temp, detail = 1772f),
                    Detail(type = Details.Mass, detail = 182.43351f),
                    Detail(type = Details.Radius, detail = 18.282f),
                    Detail(type = Details.Flux, detail = 1634f),
                    Detail(type = Details.Period, detail = 4.7947813f),
                    Detail(type = Details.ECC, detail = 0.25f),
                    Detail(type = Details.Gravity, detail = 5.349131574f),
                    Detail(type = Details.ESI, detail = 0.051067688f),
                    Detail(type = Details.Habitability, detail = 0.025533844F)
                ),
                color = Color(1f * (1 - 0.025533844f), 1f * 0.025533844f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-61 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 338.84f),
                    Detail(type = Details.Density, detail = 1.8f),
                    Detail(type = Details.Temp, detail = 1505f),
                    Detail(type = Details.Mass, detail = 335.94463f),
                    Detail(type = Details.Radius, detail = 10.077f),
                    Detail(type = Details.Flux, detail = 849.5f),
                    Detail(type = Details.Period, detail = 1.90231289f),
                    Detail(type = Details.ECC, detail = 0.113f),
                    Detail(type = Details.Gravity, detail = 32.42136249f),
                    Detail(type = Details.ESI, detail = 0.087089899f),
                    Detail(type = Details.Habitability, detail = 0.0435449495f)
                ),
                color = Color(1f * (1 - 0.0435449495f), 1f * 0.0435449495f, 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-62 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 348.815f),
                    Detail(type = Details.Density, detail = 0.77f),
                    Detail(type = Details.Temp, detail = 1512f),
                    Detail(type = Details.Mass, detail = 241.86742f),
                    Detail(type = Details.Radius, detail = 12.027f),
                    Detail(type = Details.Flux, detail = 867.2f),
                    Detail(type = Details.Period, detail = 2.6453235f),
                    Detail(type = Details.ECC, detail = 0.101f),
                    Detail(type = Details.Gravity, detail = 16.38659899f),
                    Detail(type = Details.ESI, detail = 0.074814943f),
                    Detail(type = Details.Habitability, detail = 0.0374074715f)
                ),
                color = Color(1f * (1 - 0.0374074715f), 1f * 0.0374074715f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-63 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 403.458f),
                    Detail(type = Details.Density, detail = 0.54f),
                    Detail(type = Details.Temp, detail = 1237f),
                    Detail(type = Details.Mass, detail = 195.14664f),
                    Detail(type = Details.Radius, detail = 12.543f),
                    Detail(type = Details.Flux, detail = 387.4f),
                    Detail(type = Details.Period, detail = 3.377728f),
                    Detail(type = Details.ECC, detail = 0.069f),
                    Detail(type = Details.Gravity, detail = 12.15582136f),
                    Detail(type = Details.ESI, detail = 0.07366095f),
                    Detail(type = Details.Habitability, detail = 0.136830475f)
                ),
                color = Color(1f * (1 - 0.136830475f), 1f * 0.136830475f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-64 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 651.897f),
                    Detail(type = Details.Density, detail = 0.144f),
                    Detail(type = Details.Temp, detail = 1766f),
                    Detail(type = Details.Mass, detail = 184.34048f),
                    Detail(type = Details.Radius, detail = 19.089f),
                    Detail(type = Details.Flux, detail = 1611f),
                    Detail(type = Details.Period, detail = 4.007232f),
                    Detail(type = Details.ECC, detail = 0.101f),
                    Detail(type = Details.Gravity, detail = 4.957702175f),
                    Detail(type = Details.ESI, detail = 0.049126658f),
                    Detail(type = Details.Habitability, detail = 0.024563329f)
                ),
                color = Color(1f * (1 - 0.024563329f), 1f * 0.024563329f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-68 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 202.152f),
                    Detail(type = Details.Density, detail = 0.727f),
                    Detail(type = Details.Temp, detail = 1028f),
                    Detail(type = Details.Mass, detail = 230.10892f),
                    Detail(type = Details.Radius, detail = 12.016f),
                    Detail(type = Details.Flux, detail = 184.8f),
                    Detail(type = Details.Period, detail = 2.29840551f),
                    Detail(type = Details.ECC, detail = 0.041f),
                    Detail(type = Details.Gravity, detail = 15.61851325f),
                    Detail(type = Details.ESI, detail = 0.079413065f),
                    Detail(type = Details.Habitability, detail = 0.0397065325f)
                ),
                color = Color(1f * (1 - 0.0397065325f), 1f * 0.0397065325f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-9 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 455.203f),
                    Detail(type = Details.Density, detail = 0.342f),
                    Detail(type = Details.Temp, detail = 1540f),
                    Detail(type = Details.Mass, detail = 238.05467f),
                    Detail(type = Details.Radius, detail = 15.614f),
                    Detail(type = Details.Flux, detail = 931f),
                    Detail(type = Details.Period, detail = 3.92281072f),
                    Detail(type = Details.ECC, detail = 0.084f),
                    Detail(type = Details.Gravity, detail = 9.569173551f),
                    Detail(type = Details.ESI, detail = 0.059392693f),
                    Detail(type = Details.Habitability, detail = 0.1296963465f)
                ),
                color = Color(1f * (1 - 0.1296963465f), 1f * 0.1296963465f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-17 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 400.331f),
                    Detail(type = Details.Density, detail = 3.5f),
                    Detail(type = Details.Temp, detail = 814f),
                    Detail(type = Details.Mass, detail = 425.25654f),
                    Detail(type = Details.Radius, detail = 8.709f),
                    Detail(type = Details.Flux, detail = 72.7f),
                    Detail(type = Details.Period, detail = 16.254611f),
                    Detail(type = Details.ECC, detail = 0.029f),
                    Detail(type = Details.Gravity, detail = 54.94655847f),
                    Detail(type = Details.ESI, detail = 0.11210394f),
                    Detail(type = Details.Habitability, detail = 0.05605197f)
                ),
                color = Color(1f * (1 - 0.05605197f), 1f * 0.05605197f, 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-18 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 624.368f),
                    Detail(type = Details.Density, detail = 1.02f),
                    Detail(type = Details.Temp, detail = 2060f),
                    Detail(type = Details.Mass, detail = 629.3034f),
                    Detail(type = Details.Radius, detail = 14.986f),
                    Detail(type = Details.Flux, detail = 2990f),
                    Detail(type = Details.Period, detail = 0.8378434f),
                    Detail(type = Details.ECC, detail = 0.166f),
                    Detail(type = Details.Gravity, detail = 27.46089562f),
                    Detail(type = Details.ESI, detail = 0.061138046f),
                    Detail(type = Details.Habitability, detail = 0.030569023f)
                ),
                color = Color(1f * (1 - 0.030569023f), 1f * 0.030569023f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-36 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 856.436f),
                    Detail(type = Details.Density, detail = 2.12f),
                    Detail(type = Details.Temp, detail = 1356f),
                    Detail(type = Details.Mass, detail = 1022.14128f),
                    Detail(type = Details.Radius, detail = 13.843f),
                    Detail(type = Details.Flux, detail = 561f),
                    Detail(type = Details.Period, detail = 4.1752387f),
                    Detail(type = Details.ECC, detail = 0.105f),
                    Detail(type = Details.Gravity, detail = 52.27289654f),
                    Detail(type = Details.ESI, detail = 0.07083995f),
                    Detail(type = Details.Habitability, detail = 0.035419975f)
                ),
                color = Color(1f * (1 - 0.035419975f), 1f * 0.035419975f, 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-37 A b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 211.896f),
                    Detail(type = Details.Density, detail = 0.55f),
                    Detail(type = Details.Temp, detail = 1085f),
                    Detail(type = Details.Mass, detail = 31.46517f),
                    Detail(type = Details.Radius, detail = 6.793f),
                    Detail(type = Details.Flux, detail = 229.7f),
                    Detail(type = Details.Period, detail = 4.3315366f),
                    Detail(type = Details.ECC, detail = 0f),
                    Detail(type = Details.Gravity, detail = 6.682407087f),
                    Detail(type = Details.ESI, detail = 0.12383634f),
                    Detail(type = Details.Habitability, detail = 0.06191817f)
                ),
                color = Color(1f * (1 - 0.06191817f), 1f * 0.06191817f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-38 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 343.525f),
                    Detail(type = Details.Density, detail = 0.403f),
                    Detail(type = Details.Temp, detail = 1294f),
                    Detail(type = Details.Mass, detail = 23.51942f),
                    Detail(type = Details.Radius, detail = 6.882f),
                    Detail(type = Details.Flux, detail = 464.7f),
                    Detail(type = Details.Period, detail = 4.375021f),
                    Detail(type = Details.ECC, detail = 0f),
                    Detail(type = Details.Gravity, detail = 4.86657417f),
                    Detail(type = Details.ESI, detail = 0.12013012f),
                    Detail(type = Details.Habitability, detail = 0.06006506f)
                ),
                color = Color(1f * (1 - 0.06006506f), 1f * 0.06006506f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-47 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 300.67f),
                    Detail(type = Details.Density, detail = 0.331f),
                    Detail(type = Details.Temp, detail = 853f),
                    Detail(type = Details.Mass, detail = 117.27927f),
                    Detail(type = Details.Radius, detail = 12.52f),
                    Detail(type = Details.Flux, detail = 87.73f),
                    Detail(type = Details.Period, detail = 3.9228038f),
                    Detail(type = Details.ECC, detail = 0.088f),
                    Detail(type = Details.Gravity, detail = 7.332273768f),
                    Detail(type = Details.ESI, detail = 0.083037241f),
                    Detail(type = Details.Habitability, detail = 0.0415186205f)
                ),
                color = Color(1f * (1 - 0.0415186205f), 1f * 0.0415186205f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-48 A b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 263.609f),
                    Detail(type = Details.Density, detail = 0.589f),
                    Detail(type = Details.Temp, detail = 955f),
                    Detail(type = Details.Mass, detail = 77.23269f),
                    Detail(type = Details.Radius, detail = 8.967f),
                    Detail(type = Details.Flux, detail = 137.6f),
                    Detail(type = Details.Period, detail = 3.1316666f),
                    Detail(type = Details.ECC, detail = 0.162f),
                    Detail(type = Details.Gravity, detail = 9.413104882f),
                    Detail(type = Details.ESI, detail = 0.10259886f),
                    Detail(type = Details.Habitability, detail = 0.15129943f)
                ),
                color = Color(1f * (1 - 0.15129943f), 1f * 0.15129943f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-48 B b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 263.609f),
                    Detail(type = Details.Density, detail = 0.587f),
                    Detail(type = Details.Temp, detail = 943f),
                    Detail(type = Details.Mass, detail = 78.27499f),
                    Detail(type = Details.Radius, detail = 9.087f),
                    Detail(type = Details.Flux, detail = 140.5f),
                    Detail(type = Details.Period, detail = 3.157933f),
                    Detail(type = Details.ECC, detail = 0.045f),
                    Detail(type = Details.Gravity, detail = 8.961123107f),
                    Detail(type = Details.ESI, detail = 0.13424657f),
                    Detail(type = Details.Habitability, detail = 0.067123285f)
                ),
                color = Color(1f * (1 - 0.067123285f), 1f * 0.067123285f, 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HATS-49 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 583.943f),
                    Detail(type = Details.Density, detail = 1.6f),
                    Detail(type = Details.Temp, detail = 970f),
                    Detail(type = Details.Mass, detail = 292.5969f),
                    Detail(type = Details.Radius, detail = 13.988f),
                    Detail(type = Details.Flux, detail = 1432f),
                    Detail(type = Details.Period, detail = 4.03414f),
                    Detail(type = Details.ECC, detail = 0.16f),
                    Detail(type = Details.Gravity, detail = 35.63987935f),
                    Detail(type = Details.ESI, detail = 0.114896564f),
                    Detail(type = Details.Habitability, detail = 0.057448282f)
                ),
                color = Color(1f * (1 - 0.057448282f), 1f * 0.057448282f, 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "K2-3 d",
                details = listOf(
                    Detail(type = Details.Distance, detail = 44.0727f),
                    Detail(type = Details.Density, detail = 2.1f),
                    Detail(type = Details.Temp, detail = 312f),
                    Detail(type = Details.Mass, detail = 1.6f),
                    Detail(type = Details.Radius, detail = 1.62f),
                    Detail(type = Details.Flux, detail = 1.6f),
                    Detail(type = Details.Period, detail = 44.556456f),
                    Detail(type = Details.ECC, detail = 0.097f),
                    Detail(type = Details.Gravity, detail = 5.974698979f),
                    Detail(type = Details.ESI, detail = 0.81426382f),
                    Detail(type = Details.Habitability, detail = 60.713191f / 100f)
                ),
                color = Color(1f * (1 - 60.713191f / 100f), 1f * (60.713191f / 100f), 0f),
                habitable = true,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "Kepler-22 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 194.642f),
                    Detail(type = Details.Density, detail = 5.2f),
                    Detail(type = Details.Temp, detail = 279f),
                    Detail(type = Details.Mass, detail = 9.1f),
                    Detail(type = Details.Radius, detail = 2.1f),
                    Detail(type = Details.Flux, detail = 1.01f),
                    Detail(type = Details.Period, detail = 289.863876f),
                    Detail(type = Details.ECC, detail = 0.72f),
                    Detail(type = Details.Gravity, detail = 20.22222222f),
                    Detail(type = Details.ESI, detail = 0.7216326f),
                    Detail(type = Details.Habitability, detail = 76.08162999999999f / 100f)
                ),
                color = Color(1f * (1 - 76.08162999999999f / 100f), 1f * (76.08162999999999f / 100f), 0f),
                habitable = true,
                planetType = PlanetType.Rocky
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "TOI-1452 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 30.5212f),
                    Detail(type = Details.Density, detail = 5.6f),
                    Detail(type = Details.Temp, detail = 326f),
                    Detail(type = Details.Mass, detail = 4.82f),
                    Detail(type = Details.Radius, detail = 1.672f),
                    Detail(type = Details.Flux, detail = 1.8f),
                    Detail(type = Details.Period, detail = 11.06201f),
                    Detail(type = Details.ECC, detail = 0f),
                    Detail(type = Details.Gravity, detail = 16.89664843f),
                    Detail(type = Details.ESI, detail = 0.72006129f),
                    Detail(type = Details.Habitability, detail = 76.0030645f / 100f)
                ),
                color = Color(1f * (1 - 76.0030645f / 100f), 1f * (76.0030645f / 100f), 0f),
                habitable = true,
                planetType = PlanetType.Rocky
            )
        )


        listOfPlanets.add(
            Exoplanet(
                name = "GJ 486 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 8.07426f),
                    Detail(type = Details.Density, detail = 6.79f),
                    Detail(type = Details.Temp, detail = 706f),
                    Detail(type = Details.Mass, detail = 3f),
                    Detail(type = Details.Radius, detail = 1.343f),
                    Detail(type = Details.Flux, detail = 41.3f),
                    Detail(type = Details.Period, detail = 1.4671205f),
                    Detail(type = Details.ECC, detail = 0f),
                    Detail(type = Details.Gravity, detail = 16.30028903f),
                    Detail(type = Details.ESI, detail = 0.3206508f),
                    Detail(type = Details.Habitability, detail = 36.03254f / 100f)
                ),
                color = Color(1f * (1 - 36.03254f / 100f), 1f * (36.03254f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Rocky
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 9827 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 29.661f),
                    Detail(type = Details.Density, detail = 7.19f),
                    Detail(type = Details.Temp, detail = 1175f),
                    Detail(type = Details.Mass, detail = 5.14f),
                    Detail(type = Details.Radius, detail = 1.577f),
                    Detail(type = Details.Flux, detail = 318f),
                    Detail(type = Details.Period, detail = 1.2089819f),
                    Detail(type = Details.ECC, detail = 0.063f),
                    Detail(type = Details.Gravity, detail = 20.25469967f),
                    Detail(type = Details.ESI, detail = 0.28581574f),
                    Detail(type = Details.Habitability, detail = 14.290787f / 100f)
                ),
                color = Color(1f * (1 - 14.290787f / 100f), 1f * (14.290787f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Unknown
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 9827 c",
                details = listOf(
                    Detail(type = Details.Distance, detail = 29.661f),
                    Detail(type = Details.Density, detail = 3.7f),
                    Detail(type = Details.Temp, detail = 813f),
                    Detail(type = Details.Mass, detail = 1.3f),
                    Detail(type = Details.Radius, detail = 1.241f),
                    Detail(type = Details.Flux, detail = 73f),
                    Detail(type = Details.Period, detail = 3.648096f),
                    Detail(type = Details.ECC, detail = 0.094f),
                    Detail(type = Details.Gravity, detail = 8.272292172f),
                    Detail(type = Details.ESI, detail = 0.3105048f),
                    Detail(type = Details.Habitability, detail = 25.525240000000004f / 100f)
                ),
                color = Color(1f * (1 - 25.525240000000004f / 100f), 1f * (25.525240000000004f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Unknown
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GJ 9827 d",
                details = listOf(
                    Detail(type = Details.Distance, detail = 29.661f),
                    Detail(type = Details.Density, detail = 2.34f),
                    Detail(type = Details.Temp, detail = 681f),
                    Detail(type = Details.Mass, detail = 3.53f),
                    Detail(type = Details.Radius, detail = 2.022f),
                    Detail(type = Details.Flux, detail = 36f),
                    Detail(type = Details.Period, detail = 6.20147f),
                    Detail(type = Details.ECC, detail = 0.13f),
                    Detail(type = Details.Gravity, detail = 8.461326986f),
                    Detail(type = Details.ESI, detail = 0.29620616f),
                    Detail(type = Details.Habitability, detail = 24.810308f / 100f)
                ),
                color = Color(1f * (1 - 24.810308f / 100f), 1f * (24.810308f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.IceGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "GPX-1 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 655.013f),
                    Detail(type = Details.Density, detail = 8.22242f),
                    Detail(type = Details.Temp, detail = 2300f),
                    Detail(type = Details.Mass, detail = 6261.21962f),
                    Detail(type = Details.Radius, detail = 16.477f),
                    Detail(type = Details.Flux, detail = 4650f),
                    Detail(type = Details.Period, detail = 1.744579f),
                    Detail(type = Details.ECC, detail = 0f),
                    Detail(type = Details.Gravity, detail = 226.0105592f),
                    Detail(type = Details.ESI, detail = 0.055710089f),
                    Detail(type = Details.Habitability, detail = 2.78550445f / 100f)
                ),
                color = Color(1f * (1 - 2.78550445f / 100f), 1f * (2.78550445f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.Unknown
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-25 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 300.384f),
                    Detail(type = Details.Density, detail = 0.483f),
                    Detail(type = Details.Temp, detail = 1182f),
                    Detail(type = Details.Mass, detail = 180.84527f),
                    Detail(type = Details.Radius, detail = 12.722f),
                    Detail(type = Details.Flux, detail = 325f),
                    Detail(type = Details.Period, detail = 3.65281514f),
                    Detail(type = Details.ECC, detail = 0.023f),
                    Detail(type = Details.Gravity, detail = 10.95020999f),
                    Detail(type = Details.ESI, detail = 0.073314167f),
                    Detail(type = Details.Habitability, detail = 13.66570835f / 100f)
                ),
                color = Color(1f * (1 - 13.66570835f / 100f), 1f * (13.66570835f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-29 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 313.576f),
                    Detail(type = Details.Density, detail = 0.79f),
                    Detail(type = Details.Temp, detail = 1271f),
                    Detail(type = Details.Mass, detail = 243.77561f),
                    Detail(type = Details.Radius, detail = 11.926f),
                    Detail(type = Details.Flux, detail = 433f),
                    Detail(type = Details.Period, detail = 5.72339f),
                    Detail(type = Details.ECC, detail = 0.075f),
                    Detail(type = Details.Gravity, detail = 16.79680644f),
                    Detail(type = Details.ESI, detail = 0.070682804f),
                    Detail(type = Details.Habitability, detail = 3.5341402f / 100f)
                ),
                color = Color(1f * (1 - 3.5341402f / 100f), 1f * (3.5341402f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-32 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 289.205f),
                    Detail(type = Details.Density, detail = 0.108f),
                    Detail(type = Details.Temp, detail = 1836f),
                    Detail(type = Details.Mass, detail = 216.1244f),
                    Detail(type = Details.Radius, detail = 22.194f),
                    Detail(type = Details.Flux, detail = 1841f),
                    Detail(type = Details.Period, detail = 2.1500082f),
                    Detail(type = Details.ECC, detail = 0.159f),
                    Detail(type = Details.Gravity, detail = 4.299903538f),
                    Detail(type = Details.ESI, detail = 0.042689553f),
                    Detail(type = Details.Habitability, detail = 2.13447765f / 100f)
                ),
                color = Color(1f * (1 - 2.13447765f / 100f), 1f * (2.13447765f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.add(
            Exoplanet(
                name = "HAT-P-36 b",
                details = listOf(
                    Detail(type = Details.Distance, detail = 294.419f),
                    Detail(type = Details.Density, detail = 0.955f),
                    Detail(type = Details.Temp, detail = 1820f),
                    Detail(type = Details.Mass, detail = 611.82275f),
                    Detail(type = Details.Radius, detail = 15.211f),
                    Detail(type = Details.Flux, detail = 1821f),
                    Detail(type = Details.Period, detail = 1.3273466f),
                    Detail(type = Details.ECC, detail = 0.063f),
                    Detail(type = Details.Gravity, detail = 25.91410205f),
                    Detail(type = Details.ESI, detail = 0.063609167f),
                    Detail(type = Details.Habitability, detail = 3.18045835f / 100f)
                ),
                color = Color(1f * (1 - 3.18045835f / 100f), 1f * (3.18045835f / 100f), 0f),
                habitable = false,
                planetType = PlanetType.GasGiant
            )
        )

        listOfPlanets.sortBy { it.details.last().detail }
        listOfPlanets.reverse()
        listOfPlanets.addAll(listOfPlanets)

        updateUI()
    }

    private fun fetchData() {
        val url = "https://thisisdefeiniwhwaetlelyarealwebsite.com"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                falseData()
                updateUI()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.body == null) {
                    falseData()
                    updateUI()
                    return
                }

                val gson = Gson()
                val listType = object : TypeToken<List<jsonResponse?>?>() {}.type
                val listOfResponse:List<jsonResponse> = gson.fromJson(response.body!!.string(), listType)
                
                for(response in listOfResponse)
                    listOfPlanets.add(
                        Exoplanet(name = response.name,
                            details = listOf(
                                Detail(type = Details.Distance, detail = response.distance),
                                Detail(type = Details.Density, detail = response.density),
                                Detail(type = Details.Temp, detail = response.temp),
                                Detail(type = Details.Mass, detail = response.mass),
                                Detail(type = Details.Radius, detail = response.radius),
                                Detail(type = Details.Flux, detail = response.flux),
                                Detail(type = Details.Period, detail = response.period),
                                Detail(type = Details.ECC, detail = response.eccentricity),
                                Detail(type = Details.Gravity, detail = response.gravity),
                                Detail(type = Details.ESI, detail = response.esi),
                                Detail(type = Details.Habitability, detail = response.habitability / 100f)
                            ),
                            color = Color(1f * (1 - response.habitability / 100f), 1f * (response.habitability / 100f), 0f),
                            habitable = response.habitable,
                            planetType = when(response.planetType) {
                                "Ice Giant" -> PlanetType.IceGiant
                                "Rocky" -> PlanetType.Rocky
                                "Gas Giant" -> PlanetType.GasGiant
                                else -> PlanetType.Unknown
                            }
                        )
                    )
                
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
    Gravity("g\uD83D\uDF28", "g\uD83D\uDF28 is referred to as the acceleration of gravity. Its value is 9.8 m/s^2 on Earth"),
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
