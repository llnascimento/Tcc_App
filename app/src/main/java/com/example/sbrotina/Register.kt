package com.example.sbrotina

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sbrotina.api.Endpoint
import com.example.sbrotina.databinding.ActivityRegisterBinding
import com.example.sbrotina.model.RegisterModel
import com.example.sbrotina.model.Usuario
import com.example.sbrotina.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#30C7AE")


        binding.cardback.setOnClickListener{
            val navegar = Intent(this,Starter::class.java)
            startActivity(navegar)
        }

        binding.bregister.setOnClickListener {
            val nome =  binding.etnome.text.toString().trim()
            val email = binding.etemail.text.toString().trim()
            val senha = binding.etsenha.text.toString().trim()
            val sexoUsuario = binding.etsexo.text.toString().trim()

            if (nome.isEmpty())
            {
                binding.etnome.error = "Nome required"
                binding.etnome.requestFocus()
                return@setOnClickListener
            }

            else if (email.isEmpty()){
                binding.etemail.error = "Email required"
                binding.etemail.requestFocus()
                return@setOnClickListener
            }

            else if (senha.isEmpty()){
                binding.etsenha.error = "Senha required"
                binding.etsenha.requestFocus()
                return@setOnClickListener

            }

            else if (sexoUsuario.isEmpty()){
                binding.etsexo.error = "Sexo required"
                binding.etsexo.requestFocus()
                return@setOnClickListener

            }

            else {
                register()
            }

        }


    }

    fun register(){
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://sbrotina.somee.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        var payload = RegisterModel(binding.etnome.text.toString(), binding.etemail.text.toString(), binding.etsenha.text.toString(), binding.etsexo.text.toString());
        val  callback = endpoint.create(payload);

        callback.enqueue(object : Callback<Usuario>{
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                if (response.isSuccessful == true) {

                  Toast.makeText(baseContext,"Registrado com Sucesso!!", Toast.LENGTH_LONG).show()
                  val navegar = Intent(this@Register,Starter::class.java)
                  startActivity(navegar)
                }

                else {
                    Toast.makeText(baseContext,"deu ruim!!", Toast.LENGTH_LONG).show()
                    println(response)
                }

            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(baseContext, "As informações inseridas não batem", Toast.LENGTH_LONG).show()
            }

        })
    }
}