package com.example.firemen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button2.setOnClickListener{
            var thread = NetworkThread()
            thread.start()
        }

    }
    inner class NetworkThread : Thread(){
        //private val ip = "192.168.1.6"
        private val ip = "172.30.1.111"
        private val port = 55555

        override fun run() {
            try{
                var socket = Socket(ip, port)

                var input = socket.getInputStream()
                var dis = DataInputStream(input)

                var output = socket.getOutputStream()
                var dos = DataOutputStream(output)

                var data1 = dis.readInt()
                var data2 = dis.readDouble()
                var data3 = dis.readUTF()

                socket.close()

                runOnUiThread{
                    textView5.text = "data1 : ${data1}\n"
                    textView5.append("data2 : ${data2}\n")
                    textView5.append("data3 : ${data3}\n")
                }

            }catch(e:Exception){
                e.printStackTrace()
            }
        }
    }
}