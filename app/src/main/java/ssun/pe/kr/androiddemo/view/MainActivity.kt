package ssun.pe.kr.androiddemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import ssun.pe.kr.androiddemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        supportFragmentManager.transaction {
            add(R.id.container, MainFragment(), MainFragment.TAG)
        }
    }
}
