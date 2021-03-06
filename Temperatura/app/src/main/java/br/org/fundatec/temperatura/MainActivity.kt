package br.org.fundatec.temperatura

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        lerEstacoes()
    }

    private fun lerEstacoes() {
        val url = "https://metroclimaestacoes.procempa.com.br/metroclima/seam/resource/rest/externalRest/ultimaLeitura"

        val jsonObjectRequest = GsonRequest(url, Array<Estacao>::class.java, null,
                Response.Listener { response ->
                    //textView.text = "Response: %s".format(response.toString())
                    Toast.makeText(baseContext,"Leu as Estaçoes", Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                }
        )

// Access the RequestQueue through your singleton class.
        Volley.newRequestQueue(baseContext).add(jsonObjectRequest)
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
