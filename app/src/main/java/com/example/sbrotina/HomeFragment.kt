package com.example.sbrotina

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.sbrotina.api.Endpoint
import com.example.sbrotina.model.Tarefa
import com.example.sbrotina.task.AdicionarTask
import com.example.sbrotina.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false);
        val btn = view.findViewById<CardView>(R.id.navegarAdic)
        val btn2 = view.findViewById<CardView>(R.id.carddeletar)
        val btn3 = view.findViewById<CardView>(R.id.cardsair)

        btn.setOnClickListener{
            val abrirOutraActivity = Intent(activity, AdicionarTask::class.java)
            startActivity(abrirOutraActivity)
        }

        btn3.setOnClickListener{
            showAlertDialog()
        }

        get()

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(activity, R.style.AlertDialogTheme)
            .setTitle("Sair")
            .setMessage("Deseja mesmo sair?")
            .setPositiveButton("Sair" , DialogInterface.OnClickListener { dialogInterface, i ->
                val abrirOutraActivity = Intent(activity, MainActivity::class.java)
                startActivity(abrirOutraActivity)
                Toast.makeText(activity,"Você saiu da sua conta!!", Toast.LENGTH_LONG).show()
            })
            .setNegativeButton("Cancelar", null)
            .create()
        alertDialog.show()

        // Set the button colors programmatically if needed
        val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        activity?.getColor(R.color.dialogButtonColor)?.let { positiveButton.setTextColor(it) }
        val negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        activity?.getColor(R.color.dialogButtonColor)?.let { negativeButton.setTextColor(it) }
    }


    fun get(){

        val sharedPreferences = this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val id = sharedPreferences?.getInt("id", 0)

        val retrofitClient = NetworkUtils.getRetrofitInstance("http://sbrotina.somee.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getUser(id.toString()).enqueue(object : Callback<List<Tarefa>> {
            override fun onResponse(call: Call<List<Tarefa>>, response: Response<List<Tarefa>>) {
                if (response.isSuccessful == true)
                {
                    println("Foi")
                }

                else
                {
                    println("Não foi: $response")
                }

            }

            override fun onFailure(call: Call<List<Tarefa>>, t: Throwable) {
                println("F no chat : $t")
            }

        })


    }
}