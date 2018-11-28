package net.digitapp.marketcompare.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import net.digitapp.marketcompare.R

class MainSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_search_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainSearchFragment.newInstance())
                .commitNow()
        }
    }

}
