package io.jlertele.switchs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlertele.switchs.OnSwitchChangeListener
import com.jlertele.switchs.SwitchButton

class MainActivity : AppCompatActivity(), OnSwitchChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSwitchToggleChange(isOpen: Boolean, button: SwitchButton) {
        if (isOpen) {
        } else {

        }
    }
}
