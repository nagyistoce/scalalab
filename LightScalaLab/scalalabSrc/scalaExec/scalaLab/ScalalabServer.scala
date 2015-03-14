package scalaExec.scalaLab

  import java.net._
  import java.net.Socket
  import scalaUtils.ScalaUtilitiesObject._
  import java.io.PrintWriter
  import java.io._
  import java.util.Scanner

import scalaExec.Interpreter.GlobalValues

object ScalalabServer {
 
  // init the ScalaLab server
  def init = {
scalaUtils.ScalaUtilitiesObject.thread {  // init server with a seperate thread
    GlobalValues.scalalabServerSocket =  new ServerSocket( GlobalValues.scalaLabServerPort)
    
    var saccepted = GlobalValues.scalalabServerSocket.accept   // accept a connection from the client
   
    var serverReadStream = saccepted.getInputStream   // input stream
    
    var reader = new Scanner(serverReadStream)
    
      var hasFinished = false
    while (hasFinished) {
      if (reader.hasNextLine ) {
     var line = reader.nextLine
    println("FROM CLIENT "+line)
    if (line.equals("exit"))
      hasFinished = true
      }
      
  }
  saccepted.close
}    
  }
  
  def clientForServerTest = {
    var sclient = new Socket(InetAddress.getLoopbackAddress, GlobalValues.scalaLabServerPort)
   
    
    
  var clientWriteStream = sclient.getOutputStream
  var writer = new PrintWriter(clientWriteStream)
   writer.println("hi server") 
    
    sclient.close
  
    
    
}}
