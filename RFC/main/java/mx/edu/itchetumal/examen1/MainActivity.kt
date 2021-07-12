package mx.edu.itchetumal.examen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //ArrayList para guardar RFC ya creados
    var arrayRfc = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Crear informacion para agregar a los spinners
        //Array dia
        val diaSpinner = arrayOf<String>("01","02","03","04","05","06","07","08","09","10","11","12","13",
        "14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31")
        //Array Mes
        val mesSpinner = arrayOf<String>("01","02","03","04","05","06","07","08","09","10","11","12")
        //Array año
        val anSpinner = ArrayList<String>()
        for(i in 1980 until 2020) {
            anSpinner.add(i.toString())
        }

        //Llenar Spinners
        //Llenar spinner Dia
        var adapterDia = ArrayAdapter(this,android.R.layout.simple_list_item_1,diaSpinner)
        spnDia.adapter = adapterDia
        //Llenar spinner Mes
        var adapterMes = ArrayAdapter(this,android.R.layout.simple_list_item_1,mesSpinner)
        spnMes.adapter = adapterMes
        //Llenar spinner Año
        var adapterAn = ArrayAdapter(this,android.R.layout.simple_list_item_1,anSpinner)
        spnAn.adapter = adapterAn
    }
    //FUncion Calcular RFC
    fun calcular(view: View) {
        //Obtener valores
        val nombre = txtNom.text
        val apeP = txtApeP.text
        val apeM = txtApeM.text
        val dia = spnDia.selectedItem.toString()
        val mes = spnMes.selectedItem.toString()
        val annio = spnAn.selectedItem.toString()

        //Obtener arrays para formar RFC de los spinner
        val apePArray = apeP.split("")
        val apeMCurp = apeM.split("")
        val nomCurp = nombre.split("")
        val annioArray = annio.split("")

        //Obtener primer vocal del apellido paterno
        val apeVocal = apePArray.find{it.equals("a")||it.equals("e")||it.equals("i")||it.equals("o")||it.equals("u")}

        //Crear Homoclave
        //Array de valores posibles para la homoclave
        val homArray = arrayOf("1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L",
        "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
        //Elegir 3 valores al azar del array de valores para homoclave
        val rand1 = Random.nextInt(0,homArray.size)
        val rand2 = Random.nextInt(0,homArray.size)
        val rand3 = Random.nextInt(0,homArray.size)

        //Crear RFC
        var rfc = apePArray[1] + apeVocal + apeMCurp[1] + nomCurp[1] + annioArray[3]+annioArray[4] +
                mes + dia


        //Verificar si homoclave existe
        //Crear variable si el RFC actual ya se ha creado anteriormente
        var existe = false
        //Verifica que el arreglo de RFCs almacenados tiene algun RFC
        if(arrayRfc.isNotEmpty()) {
            //Verifica el arreglo de RFCs almacenados
            for (i in 0 until arrayRfc.size){
                //Guarda RFC almacenado sin homoclave
                var rfcAnt = arrayRfc[i].chunked(10)
                //Si el arreglo anterior y el actual son iguales
                if (rfcAnt[0]==rfc){
                    //Variable que existe
                    existe = true
                    //RFC igual al RFC antiguo
                    rfc = arrayRfc[i]
                }
            }
        }
        //Si no se habia escrito el mismo RFC anteriormente
        if (existe == false){
            //RFC actual sin homoclave se le agrega los valores completos
            rfc = rfc + homArray[rand1] + homArray [rand2] + homArray [rand3]
            //Agrega RFC actual a arreglo de rfc
            arrayRfc.add(rfc)
        }
        //println(arrayRfc)
        //Imprimir RFC en pantalla y en consola
        txtCurp.text = rfc.uppercase()
        println(rfc.uppercase())
    }

    //Limpiar funcion
    fun limpiar(view: View){
        txtNom.text.clear()
        txtApeP.text.clear()
        txtApeM.text.clear()
        spnDia.setSelection(0)
        spnMes.setSelection(0)
        spnAn.setSelection(0)
        txtCurp.text = ""
    }

}