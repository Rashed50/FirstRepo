package com.shamim.kidsedu.view

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.shamim.kidsedu.R
import com.shamim.kidsedu.model.GridViewModal
import com.shamim.kidsedu.view.adapter.GridRVAdapter
const val MY_REQUEST_CODE = 300
class MainActivity : AppCompatActivity() {
    private var appUpdateManager: AppUpdateManager? = null
    private lateinit var homeGridview: GridView
    private lateinit var homeGridList: GridViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appUpdateManager = AppUpdateManagerFactory.create(this)
        checkUpdate()
        homeGridview = findViewById(R.id.idGRV)
        homeGridList = GridViewModal()

        val gridRVAdapter = GridRVAdapter(homeGridList.getDataList(), this@MainActivity)

        homeGridview.adapter = gridRVAdapter

        homeGridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this,PlayActivity::class.java)
            intent.putExtra("key", position+1)
            startActivity(intent)
        }
    }

    private val listener: InstallStateUpdatedListener =
        InstallStateUpdatedListener { installState ->
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                // After the update is downloaded, show a notification
                // and request user confirmation to restart the app.
                Log.d("TAG", "An update has been downloaded")
                Toast.makeText(this, "An update has been downloaded", Toast.LENGTH_SHORT).show()
                showSnackBarForCompleteUpdate()
            }
        }

    private fun showSnackBarForCompleteUpdate() {
        appUpdateManager!!.completeUpdate()
    }

    private fun checkUpdate() {
        // Returns an intent object that you use to check for an update.
//        if (BuildConfig.DEBUG) {
//            return
//        }

        appUpdateManager?.registerListener(listener!!)
        val appUpdateInfoTask = appUpdateManager?.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        Log.d(ContentValues.TAG, "Checking for updates")
        appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                // Request the update.
                Log.d(ContentValues.TAG, "Update available")
                appUpdateManager?.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                    AppUpdateType.FLEXIBLE,
                    // The current activity making the update request.
                    this,
                    // Include a request code to later monitor this update request.
                    MY_REQUEST_CODE
                )
            } else {
                Log.d(ContentValues.TAG, "No Update available")
            }
        }


    }

    override fun onStop() {
        appUpdateManager?.unregisterListener(listener!!)
        super.onStop()
    }
}