package mx.edu.itchetumal.curp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.SavedStateViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*
import mx.edu.itchetumal.curp.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.application?.let {

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
            //Array Sexo
            val sexoSpinner = arrayOf<String>("Hombre","Mujer")
            //Array Lugar
            val lugarSpinner = arrayOf<String>("Aguascalientes","Baja California","Baja California Sur",
                "Campeche","Chiapas","Chihuahua","Ciudad de Mexico","Coahuila","colima","Durango","Guanajuato",
                "Guerrero","Hidalgo","Jalisco","Mexico","Michoacan","Morelos","Nayarit","Nuevo Leon","Oaxaca",
                "Puebla","Queretaro","Quintana Roo","San Luis Potosi","Sinaloa","Sonora","Tabasco","Tamaulipas",
                "Tlaxcala","Veracruz","Yuacatan","Zacatecas")

            //Llenar Spinners
            //Llenar spinner Dia
            var adapterDia = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,diaSpinner)
            spnDia.adapter= adapterDia
            //Llenar spinner Mes
            var adapterMes = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,mesSpinner)
            spnMes.adapter = adapterMes
            //Llenar spinner Año
            var adapterAn = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,anSpinner)
            spnAn.adapter = adapterAn
            //Llenar Spinner Sexo
            var adapterSex = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,sexoSpinner)
            spnSex.adapter = adapterSex
            //Llenar Spinner Lugar
            var adapterLugar = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,lugarSpinner)
            spnLugar.adapter = adapterLugar

            val factory =SavedStateViewModelFactory(it,this)
            viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        }
        //Funcion para generar CURP
        btnGenerar.setOnClickListener{
            if (txtNombre.text.isNotEmpty() && txtApeP.text.isNotEmpty() && txtApeM.text.isNotEmpty()){
                viewModel.obtenerCurp(txtNombre.text.toString(),txtApeP.text.toString(),
                    txtApeM.text.toString(),spnDia.selectedItem.toString(),
                    spnMes.selectedItem.toString(),spnAn.selectedItem.toString(),
                    spnSex.selectedItem.toString(),spnLugar.selectedItem.toString())
                message.text= viewModel.getResult()
            } else {
                message.text = "Datos incompletos"
            }
        }
        //Funcion para reiniciar TextFields y Spinners
        button2.setOnClickListener{
            txtNombre.text.clear()
            txtApeP.text.clear()
            txtApeM.text.clear()
            spnDia.setSelection(0)
            spnMes.setSelection(0)
            spnAn.setSelection(0)
            spnSex.setSelection(0)
            spnLugar.setSelection(0)
            message.text = "CURP"
        }
    }

}