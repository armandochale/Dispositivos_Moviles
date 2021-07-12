package mx.edu.itchetumal.amigosecreto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //Creacion de arrayLists
    //ArayList de los amigos
    val arrayAmigos = arrayListOf("Sheila","Jhoedy","Adrian","Armando","Artemio")
    //ArrayList de cada amigo de a quien le pueden dar regalo
    val array1 = arrayListOf("Jhoedy","Adrian","Armando","Artemio")
    val array2 = arrayListOf("Sheila","Adrian","Armando","Artemio")
    val array3 = arrayListOf("Sheila","Jhoedy","Armando","Artemio")
    val array4 = arrayListOf("Sheila","Jhoedy","Adrian","Artemio")
    val array5 = arrayListOf("Sheila","Jhoedy","Adrian","Armando")
    //Matriz donde guardo a los ArrayList de los amigos
    var matrizAmigos = arrayListOf(array1,array2,array3,array4,array5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //Funcion para agregar nuevos amigos
    fun agregar(view: View) {
        //Recibe informacion del textfield del nuevo amigo
        val amigo = txtAmigo.text
        //for para llenar en todos los arreglos de los amigos al nuevo amigo
        for(i in 0 until matrizAmigos.size){
            //agrega amigo a los array de la matriz
            matrizAmigos[i].add(amigo.toString())
        }
        //Crea nuevo arraylist para nuevo amigo
        var arrayNew = ArrayList<String>()
        //Le agrega los amigos anteriores al array del nuevo amigo
        arrayNew.addAll(arrayAmigos)
        //Agrega arraylist a matriz de amigos
        matrizAmigos.add(arrayNew)
        //A la lista de los amigos que participan agrega
        arrayAmigos.add(amigo.toString())

        //Limpiar TextField
        txtAmigo.text.clear()
    }
    //Funcion para amigo secreto
    fun seleccion(view:View){
        //Crear array para guardar valores de los regalos
        var arrayTo = ArrayList<String>()

        //Copia de matrizAmigos
        var matriz2 = arrayListOf(array1)
        matriz2.clear()
        for (y in 0 until arrayAmigos.size){
            var arrayM2 = ArrayList<String>()
            for (g in 0 until matrizAmigos[y].size) {
                arrayM2.add(matrizAmigos[y].get(g))
            }
            matriz2.add(arrayM2)
        }

        //Crea variables para llenar txtFrom y txtTo
        var varFrom = "Por: \n"
        var varTo = "A: \n"
        //Obtiene tamaño de matriz, cantidad de amigos
        var tamañoMat = arrayAmigos.size
        //inicia for que avanza a traves de la matriz
        for(i in 0 until tamañoMat){
            //Guarda valor para varFrom
            varFrom = varFrom + arrayAmigos.get(i) + "\n"
            //Checa si no es el ultimo en la lista
            if(i<tamañoMat-1){
                //Obtiene numero random entre primero hasta maximo tamaño de array actual
                val randVal = Random.nextInt(matrizAmigos[i].size)
                //Guarda valor para varTo
                //Seleccionar amigo dentro del array actual con el valor random
                arrayTo.add(matrizAmigos[i].get(randVal))
                //Eliminar amigos que ya tendran regalo de todos los arraylist en la matriz
                //Obtener variable para borrar
                var borrar = matrizAmigos[i].get(randVal)
                for (o in 0 until tamañoMat){
                    matrizAmigos[o].remove(borrar)
                }
            }else{
                //Checa que el ultimo amigo no tenga a quien dar regalo
                if(matrizAmigos[i].size==0){
                    //Si no tiene a quien dar regalo intercambia con el penultimo que tenia 2 opciones
                    //Guarda el valor del penultimo
                    val ultimo = arrayTo[arrayTo.lastIndex]
                    //Remueve el valor del arrayTo
                    arrayTo.removeAt(arrayTo.lastIndex)
                    //Agrega nuevo penultimo valor que sera el nombre del ultimo amigo secreto
                    arrayTo.add(arrayAmigos.get(arrayAmigos.lastIndex))
                    //Agrega ultimo valor que era el antiguo amigo del penultimo
                    arrayTo.add(ultimo)

                } else {
                    //Si tiene a quien dar regalo el ultimo
                    arrayTo.add(matrizAmigos[i].get(matrizAmigos[i].lastIndex))

                }
            }
        }
        //LLenar varTo
        for (p in 0 until arrayTo.size){
            varTo = varTo + arrayTo[p] + "\n"
        }

        //Limpiar matriz
        matrizAmigos.clear()
        //Reiniciar matriz
        matrizAmigos.addAll(matriz2)
        //println(matriz2)

        //Llenar texto con los amigos que dan regalos y reciben
        txtFrom.text = varFrom
        txtTo.text = varTo
    }


}