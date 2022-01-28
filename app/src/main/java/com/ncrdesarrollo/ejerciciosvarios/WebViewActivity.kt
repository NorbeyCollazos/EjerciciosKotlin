package com.ncrdesarrollo.ejerciciosvarios

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {

    private val BASE_URL = "https://www.google.com/"
    private val SEARCH_PATCH = "/search?q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        //para el swipeRefrestLayout
        swipeRefresh.setOnRefreshListener {
            webview.reload()
        }

        //para el buscador
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    if (URLUtil.isValidUrl(it)){
                        if (it != null) {
                            webview.loadUrl(it)
                        }
                    }else{
                        webview.loadUrl("$BASE_URL$SEARCH_PATCH$it")
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })

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

                searchView.setQuery(url, false) //con esto mostramos la url que se ha cargado
                swipeRefresh.isRefreshing = true //se muestra el refresh
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipeRefresh.isRefreshing = false //ocultamos el refresh
            }

        }

        val settings:WebSettings = webview.settings
        settings.javaScriptEnabled = true

        webview.loadUrl(BASE_URL)

    }

    override fun onBackPressed() {
        if (webview.canGoBack()){
            webview.goBack()
        }else{
            super.onBackPressed()
        }
    }
}