package mx.edu.itchetumal.curp.ui.main

import android.R
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import kotlin.random.Random

const val RESULT_KEY = "CURP"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var arrayCurp = ArrayList<String>()

    private var result:MutableLiveData<String> = savedStateHandle.getLiveData(RESULT_KEY)

    //Funcion para obtener CURP
    fun obtenerCurp(nombre:String,apeP:String,apeM:String,dia:String,mes:String,annio:String,
                    sexo:String,lugar:String){

        //Obtener arrays para formar CURP de los spinner
        val apePArray = apeP.split("")
        val apeMArray = apeM.split("")
        val nomArray = nombre.split("")
        val annioArray = annio.split("")
        val sexArray = sexo.split("")

        //Obtener primer letra de Nombre
        val nomPrimer = nomArray[1]
        //Obtener primer letra Apellido Paterno
        val apePPrimer = apePArray[1]
        //Obtener primer letra Apellido Materno
        val apeMPrimer = apeMArray[1]
        //Obtener primer vocal del apellido paterno
        val apeVocal = apePArray.find{it.equals("a")||it.equals("e")||it.equals("i")||it.equals("o")
                ||it.equals("u")}
        //Obtener primer consonante no inicial de nombre
        val nomArray2 = nombre.drop(1).split("")
        val nomCon = nomArray2.find {it.equals("b")||it.equals("c")||it.equals("d")||it.equals("f")
                ||it.equals("g")||it.equals("h")||it.equals("j")||it.equals("k")||it.equals("l")
                ||it.equals("m")||it.equals("n")||it.equals("ñ")||it.equals("p")||it.equals("q")
                ||it.equals("r")||it.equals("s")||it.equals("t")||it.equals("v")||it.equals("w")
                ||it.equals("x")||it.equals("y")||it.equals("z")}
        //Obtener primer consonante no inicial de apellido paterno
        val apePArray2 = apeP.drop(1).split("")
        var apePCon = apePArray2.find {it.equals("b")||it.equals("c")||it.equals("d")||it.equals("f")
                ||it.equals("g")||it.equals("h")||it.equals("j")||it.equals("k")||it.equals("l")
                ||it.equals("m")||it.equals("n")||it.equals("ñ")||it.equals("p")||it.equals("q")
                ||it.equals("r")||it.equals("s")||it.equals("t")||it.equals("v")||it.equals("w")
                ||it.equals("x")||it.equals("y")||it.equals("z")}
        //Si el apellido paterno no cuenta con consonantes despues de la primera letra se convierte X
        if (apePCon == null){
            apePCon = "X"
        }
        //Obtener primer consonante no inicial de apellido materno
        val apeMArray2 = apeM.drop(1).split("")
        var apeMCon = apeMArray2.find {it.equals("b")||it.equals("c")||it.equals("d")||it.equals("f")
                ||it.equals("g")||it.equals("h")||it.equals("j")||it.equals("k")||it.equals("l")
                ||it.equals("m")||it.equals("n")||it.equals("ñ")||it.equals("p")||it.equals("q")
                ||it.equals("r")||it.equals("s")||it.equals("t")||it.equals("v")||it.equals("w")
                ||it.equals("x")||it.equals("y")||it.equals("z")}
        //Si el apellido materno no cuenta con consonantes despues de la primera letra se convierte X
        if (apeMCon == null){
            apeMCon = "X"
        }
        //Obtener Sexo
        val sexP = sexArray[1]
        //Obtener lugar
        var lugarAb = ""
        //Operacion para convertir la seleccion de spinner a las siglas que se usaran en la CURP
        when (lugar) {
            "Aguascalientes" -> lugarAb = "AS"
            "Baja California" -> lugarAb = "BC"
            "Baja California Sur" -> lugarAb = "BS"
            "Campeche" -> lugarAb = "CC"
            "Chiapas" -> lugarAb = "CS"
            "Chihuahua" -> lugarAb = "CH"
            "Ciudad de Mexico" -> lugarAb = "DF"
            "Coahuila" -> lugarAb = "CL"
            "Colima" -> lugarAb = "CM"
            "Durango" -> lugarAb = "DG"
            "Guanajuato" -> lugarAb = "GT"
            "Guerrero" -> lugarAb = "GR"
            "Hidalgo" -> lugarAb = "HG"
            "Jalisco" -> lugarAb = "JC"
            "Mexico" -> lugarAb = "MC"
            "Michoacan" -> lugarAb = "MN"
            "Morelos" -> lugarAb = "MS"
            "Nayarit" -> lugarAb = "NT"
            "Nuevo Leon" -> lugarAb = "NL"
            "Oaxaca" -> lugarAb = "OC"
            "Puebla" -> lugarAb = "PL"
            "Queretaro" -> lugarAb = "QO"
            "Quintana Roo" -> lugarAb = "QR"
            "San Luis Potosi" -> lugarAb = "SP"
            "Sinaloa" -> lugarAb = "SL"
            "Sonora" -> lugarAb = "SR"
            "Tabasco" -> lugarAb = "TC"
            "Tamaulipas" -> lugarAb = "TS"
            "Tlaxcala" -> lugarAb = "TL"
            "Veracruz" -> lugarAb = "VZ"
            "Yucatan" -> lugarAb = "YN"
            "Zacatecas" -> lugarAb = "ZS"
        }
        //Crear Homoclave
        //Array de valores posibles para la homoclave
        val homArray = arrayOf("1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
        //Elegir 2 valores al azar del array de valores para homoclave
        val rand1 = Random.nextInt(0,homArray.size)
        val rand2 = Random.nextInt(0,homArray.size)

        //Crear CURP
        var curp = apePArray[1] + apeVocal + apeMArray[1] + nomArray[1] + annioArray[3]+annioArray[4] +
                mes + dia + sexP + lugarAb + apePCon +apeMCon + nomCon

        //Verificar si homoclave existe
        //Crear variable si el CURP actual ya se ha creado anteriormente
        var existe = false
        //Verifica que el arreglo de CURPs almacenados tiene algun CURP
        if(arrayCurp.isNotEmpty()) {
            //Verifica el arreglo de CURPs almacenados
            for (i in 0 until arrayCurp.size){
                //Guarda CURP almacenado sin homoclave
                var curpAnt = arrayCurp[i].chunked(16)
                //Si el arreglo anterior y el actual son iguales
                if (curpAnt[0]==curp){
                    //Variable que existe
                    existe = true
                    //CURP igual al CURP antiguo
                    curp = arrayCurp[i]
                }
            }
        }
        //Si no se habia escrito el mismo CURP anteriormente
        if (existe == false){
            //CURP actual sin homoclave se le agrega los valores completos
            curp = curp + homArray[rand1] + homArray [rand2]
            //Agrega CURP actual a arreglo de curp
            arrayCurp.add(curp)
        }
        //println(arrayCurp)

        //Cambio a mayusculas
        var curpUp = curp.uppercase()
        //Mostrar en consola
        println(curp.uppercase())
        //Mandar a result
        result.value = curpUp
        savedStateHandle.set(RESULT_KEY,curpUp)

    }
    fun getResult():String{
        return result.value.toString()
    }

}