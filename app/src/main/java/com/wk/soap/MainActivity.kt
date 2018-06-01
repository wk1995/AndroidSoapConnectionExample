package com.wk.soap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.ksoap2.serialization.SoapObject
import org.xmlpull.v1.XmlPullParserException
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.io.IOException


class MainActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener(this)
    }

    @Synchronized
    override fun onClick(p0: View?) {
        Thread(Runnable {
            // TODO Auto-generated method stub
            // 命名空间，默认为http://tempuri.org/
            val nameSpace = "http://tempuri.org/"
            // 调用方法的名称
            val methodName = "方法"
            // EndPoint
            val endPoint = "访问的网址"
            // SOAP Action
            val soapAction = "soapAction"
            // 指定WebService的命名空间和调用方法
            val soapObject = SoapObject(nameSpace, methodName)
            // 设置需要调用WebService接口的两个参数mobileCode UserId
            soapObject.addProperty("username", "1")
            soapObject.addProperty("password", "2")
            // 生成调用WebService方法调用的soap信息，并且指定Soap版本
            val envelope = SoapSerializationEnvelope(
                    SoapEnvelope.VER10)
            envelope.bodyOut = soapObject
            // 是否调用DotNet开发的WebService
            envelope.dotNet = true
            envelope.setOutputSoapObject(soapObject)
            val transport = HttpTransportSE(endPoint)
            try {
                transport.call(soapAction, envelope)
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: XmlPullParserException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

            // 获取返回的数据
            val `object` = envelope.bodyIn as SoapObject
            // 获取返回的结果
            val result = `object`.getProperty(0).toString()
            Log.i("wk",result)
            runOnUiThread {

            }
        }).start()
    }
}
