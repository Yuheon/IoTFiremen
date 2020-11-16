package com.example.firemen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.android.synthetic.main.activity_register.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class SocketWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val log = "address"

    override fun doWork(): Result {

        try{
            var thread = NetworkThread()
            thread.start()

            return Result.success()
        }catch(e : Exception){
            e.printStackTrace()
            return Result.failure()
        }

    }

    inner class NetworkThread : Thread(){
        private val ip = "54.221.152.48"
        private val port = 4000


        override fun run() {
            try{
                var socket = Socket(ip, port)

                var input = socket.getInputStream()
                var dis = DataInputStream(input)

                var serverResponse : String = ""
                try{
                    //var c : Byte
                    var c : Int
                    do{
                        //c = dis.readByte()// c : Byte 일 때
                        //c = input.read() //아래랑 같음
                        c = dis.read()
                        serverResponse += c.toChar()
                    }while(dis.available()>0)
                    Log.i(log, "server response : $serverResponse")

                }catch(e : Exception){

                }
                if(serverResponse == "SERVER/JOIN AVAILABLE"){
                    socket.close()//?

                }
                else if(serverResponse == "SERVER/JOIN NOT AVAILABLE"){


                }


            }catch(e:Exception){
                e.printStackTrace()
            }
        }
    }
}