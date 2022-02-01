package com.ncrdesarrollo.ejerciciosvarios

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://www.google.com/"
    private val SEARCH_PATCH = "/search?q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargarWebviewBasico()
        implementarRefres()
        impplementarBuscador()

    }

    private fun cargarWebviewBasico() {

        webview.webChromeClient = object : WebChromeClient(){

        }

        webview.webViewClient = object : WebViewClient(){

            //con este metodo controlamos el manejo de carga de las nuevas url
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            //indica que una nueva pagina se empieza a cargar
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                searchView.setQuery(url, false) //con esto mostramos en el buscador la url que se ha cargado
                swipeRefresh.isRefreshing = true //se muestra el refresh
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipeRefresh.isRefreshing = false //ocultamos el refresh
            }

        }

        val settings: WebSettings = webview.settings
        settings.javaScriptEnabled = true

        webview.loadUrl(BASE_URL)
    }

    private fun implementarRefres() {
        //para el swipeRefrestLayout
        /*se debe tener en cuenta que se implemete la siguiente
         dependencia en el gradle
         implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")*/

        swipeRefresh.setOnRefreshListener {
            webview.reload()
        }
    }

    private fun impplementarBuscador() {
        //para el buscador
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    //validamos si es una url
                    if (URLUtil.isValidUrl(it)){
                        if (it != null) {
                            webview.loadUrl(it)
                        }
                    }else{
                        //realizamos la busqueda
                        webview.loadUrl("$BASE_URL$SEARCH_PATCH$it")
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })
    }

    /*aqui en el metodo onBackPressed controlamos que no se cierre la aplicacion si
    se esta navegando en diferentes paginas*/
    override fun onBackPressed() {
        if (webview.canGoBack()){
            webview.goBack()
        }else{
            super.onBackPressed()
        }
    }
}