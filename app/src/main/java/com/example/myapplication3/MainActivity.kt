package com.example.myapplication3

import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //toggle name
        val textView:TextView=findViewById(R.id.name)
        val btn:Button=findViewById(R.id.show_name)

        var showed=0
        btn.setOnClickListener{
            if(showed==0)
            {
                textView.text=getString(R.string.name)
                showed=1
            }
            else{
                textView.text=getString(R.string.empty)
                showed=0
            }

        }
        //showing list view
        val listbtn: Button=findViewById(R.id.show_list_view)
        listbtn.setOnClickListener {
            val intent=Intent(this,MainActivity3::class.java).apply{}
            startActivity(intent)
        }
        //toasting the message
        val btn2:Button=findViewById(R.id.show_message)
        val editText:EditText=findViewById(R.id.message)
        btn2.setOnClickListener{
                 Toast.makeText(this,editText.text,Toast.LENGTH_SHORT).show()

        }

        //launch an URl
        val launchbtn:Button=findViewById(R.id.launch)
        launchbtn.setOnClickListener{
            val intent= Intent().apply{
                    action=Intent.ACTION_VIEW
                data= Uri.parse("https://www.google.com")
            }
            if(intent.resolveActivity(packageManager)!=null)
            {
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"No Activity Found",Toast.LENGTH_SHORT).show()
            }
        }

        //passing data to next activity
        val nextscreenbtn:Button=findViewById(R.id.shownext)
        nextscreenbtn.setOnClickListener{
            val intent=Intent(this,MainActivity2::class.java).apply{putExtra("NAME",editText.text.toString())}
            startActivity(intent)
        }

        //make a call to access permission for calls

        val callbtn:Button=findViewById(R.id.makecall)
        callbtn.setOnClickListener{
            makephoneafterpermission(it)
        }


    }
    private fun makephoneafterpermission(view: View) {
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED)
        {
            makephone()
        }
        else{
            requestpermission(view)
        }

    }
    private fun makephone()
    {
        val tel:EditText=findViewById(R.id.phnnum)
        var num:String=getString(R.string.tel,tel.text)
        val intent=Intent().apply{
            action= ACTION_CALL
            data=Uri.parse(num)
        }
        if(intent.resolveActivity(packageManager)!=null) {
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"Not Working",Toast.LENGTH_SHORT).show()
        }
    }
    private fun requestpermission(view:View)
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.CALL_PHONE))
        {
            val snack=Snackbar.make(view,"We need your permission to make a call. "+"When asked please give the permission",Snackbar.LENGTH_INDEFINITE)
            snack.setAction("Sure",View.OnClickListener{
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),1)
            })
            snack.show()
            }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),1)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1)
        {
            if(grantResults.size==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                makephone()
            }
            else{
                Toast.makeText(this,"Permission Denied to make a call",Toast.LENGTH_SHORT).show()

            }
        }

    }




}